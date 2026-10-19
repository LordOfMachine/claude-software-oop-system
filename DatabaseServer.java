package ClaudeSoftware;

public class DatabaseServer extends ClaudeServer implements Backupable{
    private int maxDbConnections;
    private int currentDbConnections;

    public DatabaseServer(String name, int capacity, int maxConnection){
        super(name, capacity);
        this.maxDbConnections = maxConnection;
    }

    @Override
    public boolean createBackup(){
        System.out.println("Creating database backup for " + getServerName() +
                "... Backup successful!");
        return true;
    }

    @Override
    public boolean addTraffic(int traffic){
        if (this.currentDbConnections >=  this.maxDbConnections){
            System.out.println("DB Error! Max connections reached on " + getServerName());
            return false;
        }
        boolean success = super.addTraffic(traffic);
        if (success){
            this.currentDbConnections++;
        }
        return success;
    }

    @Override
    public void performHealthCheck(){
        System.out.println("Health Check [DB]: " + getServerName() +
                " | Active Connections: " + this.currentDbConnections + "/" + this.maxDbConnections +
                " | Load: " + getCurrentLoad() + "/" + getMaxCapacity());
    }


}
