package ClaudeSoftware ;

public class WebServer extends ClaudeServer {
    private boolean sslCertificateEnabled;

    public boolean getSslCertificateEnabled(){return this.sslCertificateEnabled;}

    @Override
    public void performHealthCheck(){
        System.out.println("Health Check [Web]: " + getServerName() +
                " | SSL: " + getSslCertificateEnabled() +
                " | Load: " + getCurrentLoad() + "/" + getMaxCapacity());
    }


    public WebServer (String name, int capacity,boolean sslEnabled ){
        super(name, capacity);
        this.sslCertificateEnabled = sslEnabled;
    }

    @Override
    public boolean addTraffic(int traffic){
        if(!getSslCertificateEnabled()){
            System.out.println("Security Warning! SSL missing on " + super.getServerName() + ". Traffic rejected." );
            return false;
        }
        else {
            return super.addTraffic(traffic);
        }
    }
}
