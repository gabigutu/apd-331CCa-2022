import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static ExecutorService service;
    static AtomicInteger currentNoOfTask = new AtomicInteger(0);
    public static void main(String[] args) {
        service = Executors.newFixedThreadPool(8);
        int number = 9;
        MyTask myTask = new MyTask(number);
        service.submit(myTask);
        currentNoOfTask.incrementAndGet();
        System.out.println("(Main) New task added in pool: " + number);
    }
}
