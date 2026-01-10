package advanced.thread;

public class ThreadDemo {
    public static void main(String[] args) {
        //1.创建Thread类的子类对象
        Thread t1=new MyThread();
        //2.调用Thread类的start方法，开启线程，执行run方法
        t1.start();

        for (int i = 0; i <5; i++) {
            System.out.println("Main:"+i);
        }
    }

}

class MyThread extends Thread{
    //重写run方法，设置线程任务
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread:"+i);
        }
    }
}
