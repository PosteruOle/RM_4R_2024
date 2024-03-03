package Example_01;

import javax.naming.LimitExceededException;

public class RaceConditionMain {
    public static int target=0;
    public static int LIMIT=1000000;

    public static int NUMBER_OF_THREADS=10;
    public static class MyThreadRunnable implements Runnable{
        @Override
        public void run() {
            for(int i=1;i<=LIMIT;i++){
                target++;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<NUMBER_OF_THREADS;i++){
            Thread t=new Thread(new MyThreadRunnable());
            t.start();
        }

        Thread.sleep(5000);
        System.out.println("Expected value of a target variable is equal to " + NUMBER_OF_THREADS*LIMIT);
        System.out.println("Final value of a target variable is equal to " + target);
    }
}
