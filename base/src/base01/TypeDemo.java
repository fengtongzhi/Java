package base01;

import java.util.Scanner;

/*
* 类型转换
*/
public class TypeDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        byte b=12;
        print(b);

        double d=10.0;
//        print(d);
        int k=(int)d;
        print(k);

        in.close();
    }

    public static void print(int a){
        System.out.println(a);
    }
}

/**
 * 三目运算符
 **/
class TestDemo{
    public static void main(String[] args) {
        double max=max(10.0,20.0);
        System.out.println("max="+max);

        print_jiu_mult();
    }

    public static double max(double a, double b){
        return a>b?a:b;
    }

    public static void print_jiu_mult(){
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d*%d=%d\t ", j, i, i * j);
            }
            System.out.println();
        }
    }
}
