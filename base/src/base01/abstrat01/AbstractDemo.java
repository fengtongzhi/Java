package base01.abstrat01;

public class AbstractDemo {
    public static void main(String[] args) {
        //抽象类不能创建对象
        //A a = new A();

        B b = new B("张三",20);
        b.showInfo();
        b.method01();
    }
}
