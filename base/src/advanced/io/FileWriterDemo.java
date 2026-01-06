package advanced.io;

import java.io.FileWriter;
import java.io.Writer;

public class FileWriterDemo {
    public static void main(String[] args) {
        try(
                Writer fw=new FileWriter("base/src/writer.txt",true);
                ) {
            fw.write("hello\n");
            fw.write("历经万般红尘劫，犹如凉风轻拂面。\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
