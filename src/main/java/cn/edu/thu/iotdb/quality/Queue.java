package cn.edu.thu.iotdb.quality;

public class Queue {
    static int MaxLen;
    private int front;
    private int rear;
    long[] timeQueueList;
    double[] valueQueueList;
    public Queue(int len) {
        MaxLen=len;
        timeQueueList=new long[MaxLen];
        valueQueueList=new double[MaxLen];
        front=0;
        rear=0;
    }
    public boolean isEmpty() {
        return front == rear;
    }
    public boolean isFull(){
        return ((rear + 1) % MaxLen) == front;
    }
    public long queueFrontTime() throws Exception {
        if(!isEmpty()){
            return timeQueueList[(front+1)%MaxLen];
        }
        else {
            throw new Exception();
        }
    }
    public double queueFrontValue() throws Exception {
        if(!isEmpty()){
            return valueQueueList[(front+1)%MaxLen];
        }
        else {
            throw new Exception();
        }
    }
    public long queueithTime(int i) throws Exception{
        int index=(front+i+1)%MaxLen;
        if(rear>=front&&(index>rear||index<=front)){
            System.out.println("ERROR01 Out of index");
            throw new Exception();
        }
        if(rear<front&&index>rear&&index<=front){
            System.out.println("ERROR02 Out of index");
            throw new Exception();
        }
        return timeQueueList[index];
    }
    public double queueithValue(int i) throws Exception{
        int index=(front+i+1)%MaxLen;
        if(rear>=front&&(index>rear||index<=front)){
            System.out.println("ERROR01 Out of index");
            throw new Exception();
        }
        if(rear<front&&index>rear&&index<=front){
            System.out.println("ERROR02 Out of index");
            throw new Exception();
        }
        return valueQueueList[index];
    }
    public void push(long timestamp, double value) {
        if(!isFull()){
            rear=(rear+1)%MaxLen;
            this.timeQueueList[rear]=timestamp;
            this.valueQueueList[rear]=value;
        }
        else{
            System.out.println("ERROR:Queue is Full");
        }
    }
    public void pop() {
        if(!isEmpty()){
            front=(front+1)%MaxLen;
        } else {
            System.out.println("ERROR:Queue is Empty");
        }
    }
    public int getRear(){
        return rear;
    }
    public int getFront(){
        return front;
    }
    public int getMaxLen(){
        return  MaxLen;
    }
    public int getLength(){
        if(rear>=front){
            return rear-front;
        }
        else{
            return MaxLen-(front-rear)+1;
        }
    }
}

