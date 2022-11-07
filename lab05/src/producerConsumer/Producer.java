package producerConsumer;

public class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            Main.gol.acquire();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        for (int i = 0; i < Main.N; i++) {

            buffer.put(i);

        }
        Main.plin.release();
    }

}
