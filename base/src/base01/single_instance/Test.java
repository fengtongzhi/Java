package base01.single_instance;

public class Test {
    public static void main(String[] args) {

        A a1=A.getInstance();
        A a2=A.getInstance();

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a1==a2);

        System.out.println("=============================");

        B b=B.getInstance();
        B b2=B.getInstance();

        System.out.println(b);
        System.out.println(b2);
        System.out.println(b==b2);
    }
}
