package ClaudeSoftware;

public class ClaudeManaging {
    public static void main(String[] args){
        ClaudeServer server1 = new WebServer("Alpha-Web" , 1000, true);
        ClaudeServer server2 = new DatabaseServer("Beta-DB", 500,50);
        WebServer web1 = new WebServer("Web-Beta", 800, false);
        WebServer web2 = new WebServer("Web-Gamma", 800, true);



        ClaudeCluster cluster = new ClaudeCluster();
        cluster.addServer(web1);
        cluster.addServer(web2);
        cluster.addServer(server1);
        cluster.addServer(server2);


        Backupable dbBackup = new DatabaseServer("Alpha-DB", 1000, 100);
        Backupable clusterBackup = cluster;


        RequestQueue queue = new RequestQueue();
        queue.enqueue(400);
        queue.enqueue(300);
        queue.enqueue(200);
        queue.enqueue(600);
        queue.enqueue(500);

        cluster.processQueue(queue);
        cluster.checkClusterHealth();




        server1.addTraffic(600);
        server2.addTraffic(800);
        server1.shutDown();
        server1.addTraffic(100);

        cluster.dispatchTraffic(400);
        cluster.dispatchTraffic(500);

        cluster.checkClusterHealth();

        dbBackup.createBackup();
        clusterBackup.createBackup();




        queue.enqueue(300);
        queue.enqueue(500);
        queue.enqueue(150);

        System.out.println("\n--- Operations are being removed from the queue ---");
        System.out.println("Processed traffic: " + queue.dequeue()); // 300
        System.out.println("Processed traffic: " + queue.dequeue()); // 500

        queue.enqueue(900); // A new request  has come in;

        System.out.println("Processed traffic: " + queue.dequeue()); // 150
        System.out.println("Processed traffic: " + queue.dequeue()); // 900
        System.out.println("Processed traffic: " + queue.dequeue()); // empty error

    }
}
