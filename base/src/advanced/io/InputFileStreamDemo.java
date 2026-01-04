package advanced.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputFileStreamDemo {
    public static void main(String[] args) throws IOException {
        //1.创建文件字节输入流管道与源文件接通
        InputStream is = new FileInputStream("base/src/test.txt");

        //2.使用文件字节输入流管道读取文件数据
//        int b;
//        while((b=is.read())!=-1){
//            System.out.print((char)b);
//        }

        byte[] buffer =new byte[3];
        int len;
        while((len=is.read(buffer))!=-1){
            String string = new String(buffer,0,len);
            System.out.print(string);
        }
    }
}
