package advanced.thread;

public class RunnableDemo {
    public static void main(String[] args) {
        //1.创建Runnable接口的实现类对象
        Runnable r1=new MyThread1();
        //2.创建Thread类对象，构造方法中传递Runnable接口实现类对象
        Thread t1=new Thread(r1);
        //3.调用Thread类的start方法，开启线程，执行run方法
        t1.start();

        //使用Lambda表达式简化代码
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("Lambda Thread:"+i);
            }
        }).start();

        for (int i = 0; i <5; i++) {
            System.out.println("Main:"+i);
        }
    }
}

class MyThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread:"+i);
        }
    }
}
