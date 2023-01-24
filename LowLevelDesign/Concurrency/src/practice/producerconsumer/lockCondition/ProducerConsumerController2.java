package practice.producerconsumer.lockCondition;

public class ProducerConsumerController2 {

    public static void main(String[] args) {
        MyBlockingQueue<String> myBlockingQueue = new MyBlockingQueue<>(10);

        // System.out.println("Produce some tasks");
        new Thread(new Producer2(myBlockingQueue, "myTask1")).start();
        new Thread(new Producer2(myBlockingQueue, "myTask2")).start();

        // System.out.println("Consume some tasks");
        new Thread(new Consumer2(myBlockingQueue)).start();
        new Thread(new Consumer2(myBlockingQueue)).start();

        //Trying to remove some non-existent tasks
        //thread will go to blocked state.
        new Thread(new Consumer2(myBlockingQueue)).start();
    }
}
