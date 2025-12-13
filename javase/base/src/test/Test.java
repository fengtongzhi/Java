package test;

import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 生成随机验证码
//        System.out.println("请输入验证码的长度：");
//        int n=in.nextInt();
//        String code=getCode(n);
//        System.out.println("随机生成的验证码是："+code);

        // 计算素数个数
        System.out.println("请输入起始值：");
        int start=in.nextInt();
        System.out.println("请输入结束值：");
        int end=in.nextInt();
        prime(start,end);


        in.close();
    }

    public static String getCode(int n)
    {
        String standard="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String code="";
        Random rand=new Random();
        int length=standard.length();
        for (int i = 0; i < n; i++) {
            int index=rand.nextInt(length);
            code+=standard.charAt(index);
        }

        return code;
    }

    public static void prime(int start,int end){
        int count=0;
        for (int i = start+1; i < end; i++) {
            int j=2;
            for (j = 2; j <= Math.sqrt(i); j++) {
                if(i%j==0){
                    break;
                }
            }
            if(j>Math.sqrt(i)){
                System.out.print(i+"\t");
                count++;
            }
        }
        System.out.println("\n一共有"+count+"个素数");
    }
}

class Guess_num{
    public static void main(String[] args) {
        Random random = new Random();
        int lucknum=random.nextInt(100)+1;
        Scanner in=new Scanner(System.in);
        int count=0;

        while(true){
            System.out.println("请输入您猜的数字：");
            int num=in.nextInt();

            count++;

            if(num>lucknum){
                System.out.println("猜大了");
            } else if(num<lucknum){
                System.out.println("猜小了");
            } else {
                System.out.println("恭喜你，猜对了！一共猜了"+count+"次");
                break;
            }
        }
    }
}
