package me.chengtx.hdfs.importlogs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.hdfs.DistributedFileSystem;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 5/20/2016
 */
public class ImportLogs {

    private static final String LOG_FOLDER = "c:/temp/logs";
    private static final String LOG_FOLDER_HDFS = "/ngislogs";
    private static final String ENCODING = "utf-8";
    private static final String NAME_NODE = "hdfs://10.62.89.119:8020";
    private static DistributedFileSystem hdfs;

    public static void main(String[] args) {

        Path folder = Paths.get(LOG_FOLDER);

        connectHDFS();
        createFolder();

        try {
            Files.walk(folder).forEach(s -> {
                Path file = s.getFileName();
                if (file.toString().endsWith(".log")) {
                    importLogs(s);
                    deleteLogs(s);
                }
                if (file.toString().endsWith(".gz")) {
                    Path tmp = unzipGZ(s);
                    importLogs(tmp);
                    deleteLogs(tmp);
                    deleteLogs(s);
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //readGcFile();
        disconnectHDFS();
    }

    static void readGcFile() {
        System.out.println();
        try (
                FSDataInputStream dis = hdfs.open(new org.apache.hadoop.fs.Path(LOG_FOLDER_HDFS + "/ngis_gc.log"));
                InputStreamReader isr = new InputStreamReader(dis, ENCODING);
                BufferedReader br = new BufferedReader(isr)
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void connectHDFS() {
        if (hdfs == null) {
            System.setProperty("hadoop.user.name", "ngis");
            System.setProperty("hadoop.group.name", "ngis");
            System.setProperty("hadoop.home.dir", "C:\\chengtx\\java\\hadoop\\hadoop-2.7.1");

            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", NAME_NODE);

            try {
                hdfs = new DistributedFileSystem();
//                Instant start = Instant.now();
                hdfs.initialize(new URI(NAME_NODE), conf);
//                System.out.println(Duration.between(start, Instant.now()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static void disconnectHDFS() {
        if (hdfs != null) {
            try {
                hdfs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void importLogs(Path pin) {
        //.log
        String logname = pin.getFileName().toString();
        try (
                FileInputStream is = new FileInputStream(pin.toFile());
                InputStreamReader isr = new InputStreamReader(is, ENCODING);
                BufferedReader br = new BufferedReader(isr);
                FSDataOutputStream os = hdfs.create(new org.apache.hadoop.fs.Path(LOG_FOLDER_HDFS + "/" + logname), true);
                Writer out = new OutputStreamWriter(os, ENCODING)
        ) {
            String str;
            while ((str = br.readLine()) != null) {
                out.write(str + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //deleteLogs(pin);
    }

    static Path unzipGZ(Path pin) {

        Path pout = Paths.get(pin.toString().substring(0, pin.toString().lastIndexOf('.')));
        try {
            //建立gzip压缩文件输入流
            FileInputStream fin = new FileInputStream(pin.toFile());
            //建立gzip解压工作流
            GZIPInputStream gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            FileOutputStream fout = new FileOutputStream(pout.toFile());

            int num;
            byte[] buf = new byte[1024];

            while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                fout.write(buf, 0, num);
            }

            gzin.close();
            fout.close();
            fin.close();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

        return pout;
    }

    static void deleteLogs(Path pin) {
        try {
            Files.delete(pin);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    static void createFolder() {
        org.apache.hadoop.fs.Path f = new org.apache.hadoop.fs.Path(LOG_FOLDER_HDFS);
        try {
            boolean exist = hdfs.exists(f);
            System.out.println("Whether exist of this folder:" + exist);

            // create
            if (!exist) {
                boolean isCreated = hdfs.mkdirs(f);
                if (isCreated) {
                    System.out.println("Folder created!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void deleteFolder() {
        org.apache.hadoop.fs.Path f = new org.apache.hadoop.fs.Path(LOG_FOLDER_HDFS);
        try {
            boolean exist = hdfs.exists(f);
            System.out.println("Whether exist of this folder:" + exist);

            // delete
            if (exist) {
                boolean isDeleted = hdfs.delete(f, true);
                if (isDeleted) {
                    System.out.println("Folder deleted!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
