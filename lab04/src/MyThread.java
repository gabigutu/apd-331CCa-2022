import java.util.concurrent.BrokenBarrierException;

public class MyThread extends Thread {

    private int nrThread;
    private int result;

    public MyThread(int nrThread) {
        this.nrThread = nrThread;
    }

    public int getResult() {
        return result;
    }

    public void generateResult() {
        this.result = (int)(Math.random() * 100);
    }

    @Override
    public void run() {
        System.out.println("Hello world from thread " + nrThread);

        try {
            Main.cyclicBarrier.await();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("after barrier from thread " + nrThread);
        this.generateResult();
//        synchronized (MyThread.class) {
            Main.sum++;
//        }
    }

}
