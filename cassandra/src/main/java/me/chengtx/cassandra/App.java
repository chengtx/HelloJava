package me.chengtx.cassandra;

/**
 * Hello Cassandra!
 *
 */
public class App {

	private static final String CLUSTER_IP = "10.32.127.121";

	public static void main(String[] args) {
		SimpleClient client = new SimpleClient();
		client.connect(CLUSTER_IP);
		// client.createSchema();
		// client.loadData();
		client.querySchema();
		client.close();
	}
}
