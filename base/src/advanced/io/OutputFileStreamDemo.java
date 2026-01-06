package advanced.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputFileStreamDemo {
    public static void main(String[] args) throws IOException {
        OutputStream os=new FileOutputStream("base/src/output.txt",true);

        os.write(97);
        os.write("\r\n".getBytes());

        byte[] data="hello world".getBytes();
        os.write(data);
        os.write("\r\n".getBytes());

        os.write(data,6,5);

        os.close();
    }
}
