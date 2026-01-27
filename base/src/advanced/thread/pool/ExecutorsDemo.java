package advanced.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

    }
}
