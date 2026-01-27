package advanced.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientDemo {
    public static void main(String[] args) {
        // UDP客户端代码
        try {
            Scanner in=new Scanner(System.in);
            DatagramSocket socket = new DatagramSocket();
            while(true) {
                System.out.print("请输入要发送的数据：");
                String message=in.nextLine();
                if("exit".equalsIgnoreCase(message)){
                    System.out.println("UDP客户端已退出！");
                    socket.close();
                    break;
                }
                // 创建数据包并发送数据
                byte[] bytes = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8080);
                // 发送数据
                socket.send(datagramPacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
