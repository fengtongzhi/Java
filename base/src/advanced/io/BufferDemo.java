package advanced.io;

import java.io.*;

public class BufferDemo {
    public static void main(String[] args) {
        try (
                InputStream is = new FileInputStream("base/src/test.txt");
                InputStream bis = new BufferedInputStream(is);

                OutputStream os = new FileOutputStream("base/src/test_new.txt");
                OutputStream bos = new BufferedOutputStream(os);
        ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
