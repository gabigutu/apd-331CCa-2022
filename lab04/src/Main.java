import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    static int sum = 0;
    static CyclicBarrier cyclicBarrier;

    static void extendThreadsWithBarrier() {
        MyThread myThread1 = new MyThread(76);
        myThread1.start();

        MyThread myThread2 = new MyThread(92);
        myThread2.start();

        MyThread[] myThreads = new MyThread[20];
        // [0; 1) * 10 => [0; 10) + 3 => [3; 13)
        int noThreads = (int)(Math.random() * 10) + 3;
        cyclicBarrier = new CyclicBarrier(noThreads + 2);
        System.out.println("Creating " + noThreads + " threads");
        for (int i = 0; i < noThreads; i++) {
            myThreads[i] = new MyThread(i);
            myThreads[i].start();
        }

        // will not finish because only main thread calls await
        System.out.println("Output from main thread");
        try {
            Main.cyclicBarrier.await();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("this output has to be the last one (from barrier)");

        try {
            myThread1.join();
            myThread2.join();
            for (int i = 0; i < noThreads; i++) {
                myThreads[i].join();
            }
        } catch (InterruptedException exception) {
            System.err.println("Cannot join threads");
            exception.printStackTrace();
        }

        System.out.println("MyThread1 result: " + myThread1.getResult());
        System.out.println("MyThread2 result: " + myThread2.getResult());
        for (int i = 0; i < noThreads; i++) {
            System.out.println("Thread " + i + " result: " + myThreads[i].getResult());
        }

        if (noThreads + 2 == Main.sum) {
            System.out.println("Correct");
        } else {
            System.out.println("Sum is " + Main.sum + ", but no of threads: " + (noThreads + 2));
        }
    }

    static void extendThreads() {
        MyThread myThread1 = new MyThread(76);
        myThread1.start();

        MyThread myThread2 = new MyThread(92);
        myThread2.start();

        MyThread[] myThreads = new MyThread[1000];
        // [0; 1) * 10 => [0; 10) + 3 => [3; 13)
        int noThreads = (int)(Math.random() * 10) + 990;
        System.out.println("Creating " + noThreads + " threads");
        for (int i = 0; i < noThreads; i++) {
            myThreads[i] = new MyThread(i);
            myThreads[i].start();
        }

        System.out.println("Output from main thread");

        try {
            myThread1.join();
            myThread2.join();
            for (int i = 0; i < noThreads; i++) {
                myThreads[i].join();
            }
        } catch (InterruptedException exception) {
            System.err.println("Cannot join threads");
            exception.printStackTrace();
        }

        System.out.println("this output has to be the last one");

        System.out.println("MyThread1 result: " + myThread1.getResult());
        System.out.println("MyThread2 result: " + myThread2.getResult());
        for (int i = 0; i < noThreads; i++) {
            System.out.println("Thread " + i + " result: " + myThreads[i].getResult());
        }

        if (noThreads + 2 == Main.sum) {
            System.out.println("Correct");
        } else {
            System.out.println("Sum is " + Main.sum + ", but no of threads: " + (noThreads + 2));
        }
    }

    static void runnableThreads() {
        Thread myThread1 = new Thread(new MyRunnableThread(76));
        myThread1.start();

        Thread myThread2 = new Thread(new MyRunnableThread(95));
        myThread2.start();

        Thread[] myThreads = new Thread[20];
        // [0; 1) * 10 => [0; 10) + 3 => [3; 13)
        int noThreads = (int)(Math.random() * 10) + 3;
        for (int i = 0; i < noThreads; i++) {
            myThreads[i] = new Thread(new MyRunnableThread(i));
            myThreads[i].start();
        }

        System.out.println("Output from main thread");

        try {
            myThread1.join();
            myThread2.join();
            for (int i = 0; i < noThreads; i++) {
                myThreads[i].join();
            }
        } catch (InterruptedException exception) {
            System.err.println("Cannot join threads");
            exception.printStackTrace();
        }

        System.out.println("this output has to be the last one");


    }


    public static void main(String[] args) {
        Main.extendThreadsWithBarrier();
//        Main.extendThreads();
//        Main.runnableThreads();

    }
}
