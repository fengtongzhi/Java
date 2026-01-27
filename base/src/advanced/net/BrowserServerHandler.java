package advanced.net;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class BrowserServerHandler implements Runnable {
    private Socket socket;

    public BrowserServerHandler(Socket accept) {
        this.socket = accept;
    }

    @Override
    public void run() {
        //为当前浏览器管道响应一个网页数据回去
        try{
            OutputStream os=socket.getOutputStream();
            //将字节输出流包装成打印流
            PrintStream ps=new PrintStream(os);
            //写响应的网页数据上去
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=utf-8");
            ps.println();//空行
            ps.println("<html><head><title>我的第一个网页</title></head>");
            ps.println("<body><h1>欢迎来到王者荣耀！</h1></body>");
            ps.println("</html>");
            ps.flush();
            ps.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
