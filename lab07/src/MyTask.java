import java.util.concurrent.atomic.AtomicInteger;

public class MyTask implements Runnable {

    static AtomicInteger noTasks = new AtomicInteger(0);

    private int taskId;
    private int number;

    public MyTask(int number) {
        this.taskId = noTasks.incrementAndGet();
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Running task with id: " + taskId);
        double randVal = Math.random();
        boolean cont = randVal > 0.5 ? true : false;
        System.out.println("Task " + taskId + " extracted number from pool: " + number);
        if (cont) {
            int newNumber = (int)(randVal * 3); // 0.6832321 => 68
            for (int i = 0; i < newNumber; i++) {
                    MyTask newTask = new MyTask(i);
                    Main.service.submit(newTask);
                    Main.currentNoOfTask.incrementAndGet();
                    System.out.println("Task " + taskId + " added number in pool: " + i);
            }
        } else {
            System.out.println("Task " + taskId + " will not generate a new task");
        }
        Main.currentNoOfTask.decrementAndGet();

        if (Main.currentNoOfTask.get() == 0) {
            Main.service.shutdown();
        }


    }

}
