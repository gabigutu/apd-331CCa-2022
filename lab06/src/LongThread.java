import java.util.concurrent.atomic.AtomicLong;

public class LongThread extends Thread {

    private int id;
    public long val;
    public static long sum;
    public static AtomicLong atomicSum = new AtomicLong(0);

    public LongThread(int id, long val) {
        this.id = id;
        this.val = val;
    }

    @Override
    public void run() {
        System.out.println("Thread " + id + " tries to add " + val + " to current sum = " + atomicSum.get());
        sum += val; // possible race condition
//        atomicSum.addAndGet(val);
    }

}
