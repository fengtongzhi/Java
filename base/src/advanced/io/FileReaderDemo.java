package advanced.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class FileReaderDemo {
    public static void main(String[] args){
        try(
                Reader fr=new FileReader("base/src/reader.txt");
                ){
            char[] buffer=new char[3];
            int len;
            while ((len = fr.read(buffer)) != -1) {
                String string = new String(buffer,0,len);
                System.out.println(string);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
