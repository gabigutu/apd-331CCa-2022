package producerConsumer;

import java.util.concurrent.Semaphore;

public class Main {
    public static final int N = 1000000;
    static Semaphore gol;
    static Semaphore plin;

    public static void main(String[] args) {
        gol = new Semaphore(1);
        plin = new Semaphore(0);
        Buffer buffer = new Buffer();

        Thread[] threads = new Thread[2];
        threads[0] = new Thread(new Producer(buffer));
        threads[1] = new Thread(new Consumer(buffer));

        for (int i = 0; i < 2; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 2; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
