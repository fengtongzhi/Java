package advanced.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerDemo {
    public static void main(String[] args) {
        // 创建服务端对象，注册端口
        try{
            DatagramSocket socket=new DatagramSocket(8080);
            // 创建数据包对象，用于接收客户端发送的数据
            byte[] bytes=new byte[1024*64];
            DatagramPacket datagramPacket=new DatagramPacket(bytes,bytes.length);
            // 接收客户端发送的数据
            while(true) {
                socket.receive(datagramPacket);
                // 解析数据包，并显示客户端发送的数据
                String data = new String(bytes, 0, datagramPacket.getLength());
                System.out.println("客户端说：" + data);

                String ip = datagramPacket.getAddress().getHostAddress();
                int port = datagramPacket.getPort();
                System.out.println("客户端的IP地址：" + ip);
                System.out.println("客户端的端口号：" + port);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
