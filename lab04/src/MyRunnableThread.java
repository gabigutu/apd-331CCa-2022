public class MyRunnableThread implements Runnable {

    private int nrThread;
    private int result;

    public MyRunnableThread(int nrThread) {
        this.nrThread = nrThread;
    }

    @Override
    public void run() {
        System.out.println("Hello world from thread " + nrThread);
        this.generateResult();
    }

    public int getResult() {
        return result;
    }

    private void generateResult() {
        this.result = (int)(Math.random() * 100);
    }
}
