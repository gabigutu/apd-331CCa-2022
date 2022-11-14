import java.util.concurrent.Semaphore;

public class SemNegative extends Thread {

    private int id;
    private Semaphore semaphore;

    public SemNegative(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        if (this.id == 0) {
            try {
                semaphore.acquire();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("This message should be shown after min 11 other message");
        } else {
            System.out.println("Other message from thread " + this.id);
            semaphore.release(); //
        }

    }
}
