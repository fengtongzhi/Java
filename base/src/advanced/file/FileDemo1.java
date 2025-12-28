package advanced.file;

import java.io.File;

public class FileDemo1 {
    public static void main(String[] args) {
        File f1=new File("C:\\Users\\passion\\Desktop");

        System.out.println(f1.exists());
        System.out.println(f1.isDirectory());
        System.out.println(f1.isFile());
        System.out.println(f1.length());

        File f2=new File("C:\\Users\\passion\\Desktop\\文件暂存处");
        String[] names=f2.list();
        if (names != null) {
            for(String name: names){
                System.out.println(name);
            }
        }

        System.out.println("----------------------获取文件详细信息-------------------------");
        File[] files = f2.listFiles();
        if (files != null) {
            for(File file:files){
                System.out.println(file.getName() + " : " + file.length());
            }
        }
    }
}
