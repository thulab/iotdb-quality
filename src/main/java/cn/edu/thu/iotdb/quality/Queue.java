package cn.edu.thu.iotdb.quality;

import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class Queue {
    static int MaxLen;
    private int front;
    private int rear;
    TSDataType datatype;
    long[] timeQueueList;
    double[] doubleValueQueueList;
    float[] floatValueQueueList;
    int[] intValueQueueList;
    long[] longValueQueueList;
    public Queue(int len, TSDataType type) {
        MaxLen=len;
        timeQueueList=new long[MaxLen];
        datatype=type;
        switch(datatype){
            case INT32:
                intValueQueueList=new int[MaxLen];
                break;
            case INT64:
                longValueQueueList=new long[MaxLen];
                break;
            case FLOAT:
                floatValueQueueList=new float[MaxLen];
                break;
            case DOUBLE:
                doubleValueQueueList=new double[MaxLen];
                break;
        }
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
            switch(datatype){
                case INT32:
                    return intValueQueueList[(front+1)%MaxLen];
                case INT64:
                    return (double) longValueQueueList[(front+1)%MaxLen];
                case FLOAT:
                    return floatValueQueueList[(front+1)%MaxLen];
                case DOUBLE:
                    return doubleValueQueueList[(front+1)%MaxLen];
            }
            return 0;
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
    public Object queueithValue(int i) throws Exception{
        int index=(front+i+1)%MaxLen;
        if(rear>=front&&(index>rear||index<=front)){
            System.out.println("ERROR01 Out of index");
            throw new Exception();
        }
        if(rear<front&&index>rear&&index<=front){
            System.out.println("ERROR02 Out of index");
            throw new Exception();
        }
        switch(datatype){
            case INT32:
                return intValueQueueList[index];
            case INT64:
                return longValueQueueList[index];
            case FLOAT:
                return floatValueQueueList[index];
            case DOUBLE:
                return doubleValueQueueList[index];
        }
        return 0;
    }
    public void push(long timestamp, Object value) {
        if(!isFull()){
            rear=(rear+1)%MaxLen;
            this.timeQueueList[rear]=timestamp;
            switch(datatype){
                case INT32:
                    intValueQueueList[rear]= (int) value;
                case INT64:
                    longValueQueueList[rear]= (long) value;
                case FLOAT:
                    floatValueQueueList[rear] = (float) value;
                case DOUBLE:
                    doubleValueQueueList[rear] = (double) value;
            }
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

