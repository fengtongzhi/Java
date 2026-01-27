package advanced.net;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerDemo {
    public static void main(String[] args) {
        // TCP服务器代码
        try{
            //1.创建服务端Socket对象，绑定端口号
            ServerSocket socket=new ServerSocket(9999);
            System.out.println("服务器启动，等待客户端连接...");
            //2.调用accept方法，监听客户端连接请求，返回Socket对象
            Socket accept = socket.accept();
            System.out.println("客户端已连接！");
            //3.获取Socket的输入流，读取客户端发送的数据
            byte[] bytes=new byte[1024];
            BufferedInputStream bis=new BufferedInputStream(accept.getInputStream());
            while(true) {
                int len = bis.read(bytes);
                String data = new String(bytes, 0, len);
                if("exit".equalsIgnoreCase(data)){
                    System.out.println("TCP服务器已关闭与客户端的连接！");
                    bis.close();
                    accept.close();
                    socket.close();
                    break;
                }
                System.out.println("客户端说：" + data);

                System.out.println("客户端ip:" + accept.getInetAddress().getHostAddress());
                System.out.println("客户端port:" + accept.getPort());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
