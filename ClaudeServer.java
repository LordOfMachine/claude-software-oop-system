package ClaudeSoftware;

public abstract class ClaudeServer {
    private String serverName;
    private int maxCapacity, currentLoad;
    private boolean isOnline;

    public abstract void performHealthCheck();

    public void shutDown(){this.isOnline = false;}

    public void bootUp(){this.isOnline = true;}

    public String getServerName(){return this.serverName;}

    public int getCurrentLoad(){return this.currentLoad;}

    public int getMaxCapacity(){return this.maxCapacity;}

    public boolean getIsOnline(){return this.isOnline;}

    public ClaudeServer(String name, int capacity){
        this.serverName = name;
        this.maxCapacity = capacity;
        this.currentLoad = 0; // A new server starts with a load of 0.
        this.isOnline = true; // Starts plugged into the power outlet
    }

    public boolean addTraffic(int incomingTraffic){
        if (!this.isOnline){
            System.out.println("Server " + this.serverName + " is down.");
            return false;
        }
        else if (this.currentLoad + incomingTraffic > this.maxCapacity){
            System.out.println("Critical Error! " + this.serverName + " capacity exceeded. Incoming traffic rejected.");
            return false;
        }
        else {
            this.currentLoad += incomingTraffic;
            System.out.println("Traffic has been successfully redirected. " + this.currentLoad);
            return true;
        }
    }


}
