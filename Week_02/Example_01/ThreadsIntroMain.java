package Example_01;

public class ThreadsIntroMain {

    // There are two ways to create a thread in Java:
    //      1. extend Thread class;
    //      2. implement Runnable interface.

    // First option!
    // We will create our own class that will inherit (extend) Thread class...
    public static class Mythread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hi there from thread" + Thread.currentThread().threadId() + "!");
        }
    }

    // Second option!
    // We will create our own class that will implement Runnable interface...
    public static class MyThreadRunnable implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hi there from thread" + Thread.currentThread().threadId() + "!");
        }
    }
    public static void main(String[] args) {
        System.out.println("Hi there from main thread" + Thread.currentThread().threadId() + "!");

        Thread t1=new Mythread();
        Thread t2=new Thread(new MyThreadRunnable());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finishing main!");
    }
}
