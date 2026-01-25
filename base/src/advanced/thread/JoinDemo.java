package advanced.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class JoinDemo {
    public static void main(String[] args) {
        Callable<String> c = new MyThread5();
        FutureTask<String> futureTask= new FutureTask<>(c);
        Thread t = new Thread(futureTask);
        t.start();

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            if(i==1){
                try {
                    t.join();
                    // 子线程已结束，获取并打印返回值
                    String result = futureTask.get();
                    System.out.println(result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}

class MyThread5 implements Callable<String> {
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            sum += i;
        }
        return "1到5的和是：" + sum;
    }
}
