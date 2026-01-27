package advanced.net;

import java.io.BufferedInputStream;
import java.net.Socket;

public class MTCPServerHandler implements Runnable {
    private Socket socket;
    public MTCPServerHandler(Socket accept) {
        this.socket=accept;
    }
    @Override
    public void run() {
        //处理与客户端的通信
        try{
            //3.获取输入流，读取客户端发送的数据
            BufferedInputStream bis=new BufferedInputStream(socket.getInputStream());
            byte[] buffer=new byte[1024];
            int len;
            while((len=bis.read(buffer))!=-1){
                String message=new String(buffer,0,len);
                System.out.println("收到客户端数据："+message);
                System.out.println("客户端ip:" + socket.getInetAddress().getHostAddress());
                System.out.println("客户端port:" + socket.getPort());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
