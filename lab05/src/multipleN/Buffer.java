package multipleN;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    Queue<Integer> queue;
    static Semaphore gol;
    static Semaphore plin;

    public Buffer(int size) {
        queue = new LimitedQueue<>(size);
        gol = new Semaphore(size);
        plin = new Semaphore(0);
    }

    public void put(int value) {
        try {
            gol.acquire();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        synchronized (queue) {
            queue.add(value);

        }
        plin.release();
    }

    public int get() {
        try {
            plin.acquire();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        int a = -1;
        synchronized (queue) {
            Integer result = queue.poll();
            if (result != null) {
                a = result;
            }
        }
        gol.release();
        return a;
    }
}
