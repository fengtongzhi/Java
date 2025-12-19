package base01.enum_demo;

public class Test {
    public static void main(String[] args) {
        A a1=A.X;
        A a2=A.Y;

        System.out.println(a1);
        System.out.println(a2);

        //获取枚举常量的名称和序号
        System.out.println(a1.name());
        System.out.println(a1.ordinal());
    }
}
