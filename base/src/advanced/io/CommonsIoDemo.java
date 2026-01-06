package advanced.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CommonsIoDemo {
    public static void main(String[] args) {
        try {
            FileUtils.copyFile(new File("base/src/test.txt"), new File("base/src/test_copy.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
