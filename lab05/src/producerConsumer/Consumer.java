package producerConsumer;

public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            Main.plin.acquire();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for (int i = 0; i < Main.N; i++) {
            int value = buffer.get();
            if (value != i) {
                System.out.println("Wrong value. I was supposed to get " + i + " but I received " + value);
                System.exit(1);
            }
        }
        Main.gol.release();
        System.out.println("I finished Correctly");
    }
}
