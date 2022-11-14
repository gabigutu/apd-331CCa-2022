import java.util.concurrent.Semaphore;

public class Main {

    public static void semaphoreTest() {
        int noThreads = 100;
        Semaphore semaphore = new Semaphore(-11); // -11

        Thread[] myThreads = new SemNegative[noThreads];
        for (int i = 0; i < noThreads; i++) {
            myThreads[i] = new SemNegative(i, semaphore);
            myThreads[i].start();
        }

        // 12 thread + thread main
        System.out.println("Message from main thread");

        for (int i = 0; i < noThreads; i++) {
            try {
                myThreads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    static void atomicLongTest() {
        int noThreads = 1 * 1000 * 100; // 1 * 1000 * 100 ?
        Thread[] myThreads = new LongThread[noThreads];
        for (int i = 0; i < noThreads; i++) {
            myThreads[i] = new LongThread(i, i);
            myThreads[i].start();
        }

        for (int i = 0; i < noThreads; i++) {
            try {
                myThreads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        // suma primelor n numere = n * (n+1) /2
        long expectedSum = (long)(noThreads - 1) * (long)noThreads / 2;
        if (LongThread.sum == expectedSum) {
//        if (LongThread.atomicSum.get() == expectedSum) {
            System.out.println("Correct");
        } else {
            System.out.println("Expected sum = " + expectedSum);
//            System.out.println("Calculated sum = " + LongThread.atomicSum.get());
            System.out.println("Calculated sum = " + LongThread.sum);
        }

    }

    public static void main(String[] args) {
//        Main.semaphoreTest();
        Main.atomicLongTest();


    }
}
