package ClaudeSoftware;

public class RequestQueue {
    private RequestNode head;
    private RequestNode tail;

    public RequestQueue(){
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public void enqueue(int traffic){
        RequestNode newNode = new RequestNode(traffic);

        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            this.tail.setNext(newNode);
            this.tail = newNode;
        }

        System.out.println("Request with " + traffic + " traffic enqueued.");
    }

    public int dequeue(){
        if (isEmpty()){
            System.out.println("Queue is empty!");
            return -1;
        }

        int result = this.head.getTraffic();

        this.head = this.head.getNext();

        if (this.head == null){
            this.tail = null;
        }
        return result;
    }
}

