package me.chengtx.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author chengt4
 */
public class HDFSTest {

    private static final String NAME_NODE = "hdfs://10.62.89.119:8020";
    private static DistributedFileSystem hdfs;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("HADOOP_USER_NAME", "gpadmin");
        System.setProperty("hadoop.home.dir", "C:\\chengtx\\java\\hadoop\\hadoop-2.7.1");

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", NAME_NODE);

        try {
            hdfs = new DistributedFileSystem();
            Instant start = Instant.now();
            hdfs.initialize(new URI(NAME_NODE), conf);
            System.out.println(Duration.between(start, Instant.now()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        hdfs.close();
    }

    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * list all data nodes name
     */

    @Test
    public void listDataNodeInfo() {
        System.out.println();
        try {
            DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
            String[] names = new String[dataNodeStats.length];
            System.out.println("List of all the data nodes in the HDFS cluster:");

            for (int i = 0; i < names.length; i++) {
                names[i] = dataNodeStats[i].getHostName();
                System.out.println(names[i]);
            }
            System.out.println(hdfs.getUri().toString());
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }


    /**
     * check if file exist
     */
    @Test
    public void checkFileExist() {
        System.out.println();
        try {
            Path homePath = hdfs.getHomeDirectory();
            System.out.println("main path:" + homePath.toString());
            assertEquals(NAME_NODE + "/user/root", homePath.toString());

            Path f = new Path("/user/chengtx");
            boolean exist = hdfs.exists(f);
            System.out.println("Whether exist of this file:" + exist);

            // delete /usr/root
            if (exist) {
                boolean isDeleted = hdfs.delete(f, true);
                if (isDeleted) {
                    System.out.println("Delete success!");
                }
            }


            // create /usr/root
            if (!exist) {
                boolean isCreated = hdfs.mkdirs(f);
                if (isCreated) {
                    System.out.println("Create success!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * create file to HDFS
     */
    @Test
    public void createFile() {
        System.out.println();
        try (
                FSDataOutputStream os = hdfs.create(new Path("/file03"), true);
                Writer out = new OutputStreamWriter(os, "utf-8")
        ) {
            System.out.println("Start to create and write: " + new Path("/file03").getName() + " to hdfs");
            out.write("jvm"+"\n");
            out.write("jvm"+"\n");
            out.write("jvm"+"\n");
            out.write("jvm"+"\n");
            out.write("jvm"+"\n");
            out.write("java"+"\n");
            out.write("java"+"\n");
            out.write("java"+"\n");
            out.write("gc"+"\n");
            out.write("gc"+"\n");
            out.write("gc"+"\n");
            out.write("gc"+"\n");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * read local file to HDFS
     * the file should be UTF-8
     */
    @Test
    public void copyFileToHDFS() {
        System.out.println();
        try (
                FileInputStream is = new FileInputStream(new File("C:\\IFRToolLog.txt"));
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                FSDataOutputStream os = hdfs.create(new Path("/file01"), true);
                Writer out = new OutputStreamWriter(os, "utf-8")
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                out.write(str + "\n");
            }
            System.out.println("Write content of file " + new File("C:\\IFRToolLog.txt").getName() + " to hdfs file " + new Path("/file01").getName() + " success");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * get the location of file block
     */
    @Test
    public void getLocation() {
        try {
            Path f = new Path("/file02");
            FileStatus fileStatus = hdfs.getFileStatus(f);

            BlockLocation[] blkLocations = hdfs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
            for (BlockLocation currentLocation : blkLocations) {
                String[] hosts = currentLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }

            // get last modified date
            long modifyTime = fileStatus.getModificationTime();
            Date d = new Date(modifyTime);
            System.out.println(d);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * read file from HDFS
     */
    @Test
    public void readFileFromHDFS() {
        System.out.println();
        try (
                FSDataInputStream dis = hdfs.open(new Path("/file03"));
                InputStreamReader isr = new InputStreamReader(dis, "utf-8");
                BufferedReader br = new BufferedReader(isr)
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * append content to file
     */
    @Test
    public void appendFileToHDFS() {
        System.out.println();
        Path f = new Path("/file02");
        try {
            boolean exist = hdfs.exists(f);
            if (!exist) {
                boolean isCreated = hdfs.createNewFile(f);
                if (isCreated) {
                    System.out.println("Create success!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        try (
                FSDataOutputStream fs = hdfs.append(f);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fs, "utf-8"));
        ) {
            for (int i = 0; i < 10; i++) {
                out.write(Instant.now() + ": Hello, HDFS!");
                out.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
