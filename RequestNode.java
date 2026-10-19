package ClaudeSoftware;

public class RequestNode {
    private int traffic;
    private RequestNode next;

    public RequestNode(int traffic){
        this.traffic = traffic;
        this.next = null;
    }

    public int getTraffic() {
        return this.traffic;
    }

    public RequestNode getNext() {
        return this.next;
    }

    public void setNext(RequestNode next) {
        this.next = next;
    }

}
