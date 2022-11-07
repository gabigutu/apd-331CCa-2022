import java.util.concurrent.Semaphore;

public class Main {

    static int a = 0;
    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        MyThread[] myThreads = new MyThread[101];
        for (int i = 1; i <= 100; i++) {
            myThreads[i] = new MyThread(i);
        }
        for (int i = 1; i <= 100; i++) {
            myThreads[i].start();
        }


        // zona paralela (thread-ul main si celelalte thread-uri)
        try {
            for (int i = 1; i <= 100; i++) {
                myThreads[i].join();
            }

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("a = " + Main.a);

        // zona secventiala (doar thread-ul main)
    }
}
