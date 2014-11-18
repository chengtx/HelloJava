package me.chengtx.hdfs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author chengt4
 *
 */
public class HDFSTest {

	private static final String NAME_NODE = "hdfs://10.32.127.112:8020";
	private static Configuration conf;
	private static FileSystem fs;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		System.setProperty("HADOOP_USER_NAME", "root");
		conf = new Configuration();
		conf.set("fs.defaultFS", NAME_NODE);

		try {
			fs = FileSystem.get(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

	/**
	 * 列出所有DataNode的名字信息
	 */
	/*
	 * @Test public void listDataNodeInfo() { try { DatanodeInfo[] dataNodeStats
	 * = fs.getDataNodeStats(); String[] names = new
	 * String[dataNodeStats.length];
	 * System.out.println("List of all the datanode in the HDFS cluster:");
	 * 
	 * for (int i = 0; i < names.length; i++) { names[i] =
	 * dataNodeStats[i].getHostName(); System.out.println(names[i]); }
	 * System.out.println(fs.getUri().toString()); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

	/**
	 * 查看文件是否存在
	 */
	@Test
	public void checkFileExist() {
		try {
			Path homePath = fs.getHomeDirectory();
			System.out.println("main path:" + homePath.toString());
			assertEquals(NAME_NODE + "/user/root", homePath.toString());

			Path f = new Path("/user/root");
			boolean exist = fs.exists(f);
			System.out.println("Whether exist of this file:" + exist);

			// 删除文件
			if (!exist) {
				boolean isCreated = fs.mkdirs(f);
				if (isCreated) {
					System.out.println("Create success!");
				}
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * 创建文件到HDFS系统上
	 */
	// @Test
	public void createFile() {
		try {
			Path f = new Path("/file03");
			System.out.println("Create and Write :" + f.getName() + " to hdfs");

			FSDataOutputStream os = fs.create(f, true);
			Writer out = new OutputStreamWriter(os, "utf-8");//
			out.write("Hello, HDFS!");
			out.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取本地文件到HDFS系统<br>
	 * 请保证文件格式一直是UTF-8，从本地->HDFS
	 */
	public void copyFileToHDFS() {
		try {
			Path f = new Path("/user/xxx/input02/file01");
			File file = new File("E:\\hadoopTest\\temporary.txt");

			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			FSDataOutputStream os = fs.create(f, true);
			Writer out = new OutputStreamWriter(os, "utf-8");

			String str = "";
			while ((str = br.readLine()) != null) {
				out.write(str + "\n");
			}
			br.close();
			isr.close();
			is.close();
			out.close();
			os.close();
			System.out.println("Write content of file " + file.getName()
					+ " to hdfs file " + f.getName() + " success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得文件块所在的位置..
	 */
	public void getLocation() {
		try {
			Path f = new Path("/user/xxx/input02/file01");
			FileStatus fileStatus = fs.getFileStatus(f);

			BlockLocation[] blkLocations = fs.getFileBlockLocations(fileStatus,
					0, fileStatus.getLen());
			for (BlockLocation currentLocation : blkLocations) {
				String[] hosts = currentLocation.getHosts();
				for (String host : hosts) {
					System.out.println(host);
				}
			}

			// 取得最后修改时间
			long modifyTime = fileStatus.getModificationTime();
			Date d = new Date(modifyTime);
			System.out.println(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取hdfs中的文件内容
	 */
	public void readFileFromHdfs() {
		try {
			Path f = new Path("/user/xxx/input02/file01");

			FSDataInputStream dis = fs.open(f);
			InputStreamReader isr = new InputStreamReader(dis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
			br.close();
			isr.close();
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
