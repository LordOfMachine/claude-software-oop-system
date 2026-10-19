package ClaudeSoftware;

import java.util.ArrayList;

public class ClaudeCluster implements Backupable {
    private ArrayList<ClaudeServer> servers = new ArrayList<>();

    public ClaudeCluster(){
        this.servers = new ArrayList<>();
    }

    @Override
    public boolean createBackup(){
        System.out.println("Creating full cluster topology backup with " + servers.size() +
                " servers... Cluster Backup successful!");
        return true;
    }

    public void addServer(ClaudeServer server){
        this.servers.add(server);
        System.out.println("Server " + server.getServerName() + " added to cluster.");
    }

    public void listServers(){
        for(ClaudeServer s : this.servers){
            System.out.println("Current load is " + s.getCurrentLoad());
            System.out.println("Is server online: " + s.getIsOnline());
        }
    }

    public void dispatchTraffic(int traffic){
        boolean isHandled = false;

        for (ClaudeServer s : this.servers){
            if (s.getIsOnline() && s.addTraffic(traffic)){
                isHandled = true;
                break;
            }
        }
        if(!isHandled){
            System.out.println("All servers are overloaded or offline! Request dropped");
        }
    }

    public void checkClusterHealth(){
        System.out.println("\n--- CLUSTER HEALTH REPORT ---");
        for (ClaudeServer s: this.servers){
            s.performHealthCheck();
        }
    }

    public void processQueue(RequestQueue queue){
        System.out.println("\n--- CLUSTER IS PROCESSING THE QUEUE ---");

        while(!queue.isEmpty()){
            int incomingTraffic = queue.dequeue();
            System.out.println("-> Pulled from queue: " + incomingTraffic);

            this.dispatchTraffic(incomingTraffic);
        }
        System.out.println("--- QUEUE PROCESSING COMPLETE ---");
    }

}
