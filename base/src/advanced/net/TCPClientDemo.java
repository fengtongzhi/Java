package advanced.net;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;

public class TCPClientDemo {
    public static void main(String[] args) {
        // TCP客户端代码
        try{
            Socket socket=new Socket("127.0.0.1",9999);

            //从Socket获取输出流，向服务器发送数据
            OutputStream os=socket.getOutputStream();
            BufferedOutputStream bos=new BufferedOutputStream(os);

            Scanner in = new Scanner(System.in);
            while(true){
                System.out.print("请输入要发送的数据：");
                String message=in.nextLine();
                bos.write(message.getBytes());
                bos.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
