package base01;

import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {

        Scanner in=new Scanner(System.in);

        System.out.println("请输入一个用户名：");
        String name=in.nextLine();
        System.out.println("请输入一个年龄：");
        int age=in.nextInt();
        System.out.printf("用户名：%s，年龄：%d%n",name,age);

        in.close();
    }
}
