package advanced.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) {
        //创建Callable接口的实现类对象
        MyCallable myCallable=new MyCallable(100);
        //创建FutureTask类对象，构造方法中传递Callable接口实现类对象
        FutureTask<Integer> ft=new FutureTask<>(myCallable);
        //创建Thread类对象，构造方法中传递FutureTask类对象
        Thread t1=new Thread(ft);
        //调用Thread类的start方法，开启线程，执行call方法
        t1.start();

        //若线程未执行完成，主线程阻塞等待，获取线程执行结果
        try {
            //获取线程执行结果
            Integer sum=ft.get();
            System.out.println("1到100的和："+sum);
        } catch (Exception e) {
            e.printStackTrace();
    }
}

class MyCallable implements Callable<Integer>{
    private int n;

    public MyCallable(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i = 1; i <=n; i++) {
            sum+=i;
        }
        return sum;
    }
}
