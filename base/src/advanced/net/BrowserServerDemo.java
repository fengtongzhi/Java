package advanced.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BrowserServerDemo {
    public static void main(String[] args) {
        System.out.println("请在浏览器中输入服务器的URL以访问Web服务器。");
        try{
            //1.创建服务端Socket对象，绑定端口号
            ServerSocket socket=new ServerSocket(8080);
            System.out.println("服务器启动，等待客户端连接...");

            //创建一个线程池
            ThreadPoolExecutor pool=new ThreadPoolExecutor(3,5,10,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(5),
                    new ThreadPoolExecutor.AbortPolicy());

            while(true) {
                //2.调用accept方法，监听客户端连接请求，返回Socket对象
                Socket accept = socket.accept();
                System.out.println("客户端已连接！");

                //启动一个线程处理与该客户端的通信
                Runnable task=new BrowserServerHandler(accept);
                pool.execute(task);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
