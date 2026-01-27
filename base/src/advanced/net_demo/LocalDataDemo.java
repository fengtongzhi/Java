package advanced.net_demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDataDemo {
    public static void main(String[] args) {
        LocalDateTime now=LocalDateTime.now();
        System.out.println("当前时间："+now);

        // 格式化输出
        DateTimeFormatter dft=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss EEE a");
        String format=dft.format(now);
        System.out.println("格式化后时间："+format);
    }
}
