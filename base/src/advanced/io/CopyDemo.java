package advanced.io;

import java.io.*;

public class CopyDemo {
    public static void main(String[] args) {
        try {
            copyFile("base/image/demo01.jpeg","base/image/demo01_new.jpeg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyFile(String srcPath,String destPath) throws IOException {
        InputStream is=null;
        OutputStream os=null;
        try {
            is=new FileInputStream(srcPath);
            os=new FileOutputStream(destPath);

            byte[] buffer=new byte[1024];
            int len;
            while((len=is.read(buffer))!=-1){
                os.write(buffer,0,len);
            }
            System.out.println("文件复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFile_improve(String srcPath,String destPath) throws IOException {
        try (
            InputStream is=new FileInputStream(srcPath);
            OutputStream os=new FileOutputStream(destPath);
                ){
            byte[] buffer=new byte[1024];
            int len;
            while((len=is.read(buffer))!=-1){
                os.write(buffer,0,len);
            }
            System.out.println("文件复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
