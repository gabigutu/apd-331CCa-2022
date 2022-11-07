public class MyThread extends Thread {

    private int threadId;

    public MyThread(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        System.out.println("Message from thread " + threadId);
        Main.a++;
        try {
            Main.semaphore.acquire();
        } catch (InterruptedException exception) {
            System.err.println("Thread " + threadId + " interrupted");
            exception.printStackTrace();
        }
        System.out.println("Thread " + threadId + " acquired semaphore; available permits: " + Main.semaphore.availablePermits());
//        for (int i = 0; i < 100; i++) {
//            System.out.println("Hello no " + i + " from thread " + threadId);
//        }
//        Main.semaphore.release();
    }
}
