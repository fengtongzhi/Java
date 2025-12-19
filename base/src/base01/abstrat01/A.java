package base01.abstrat01;

public abstract class A {
    private String name;
    private int age;

    public A(String name,int age) {}

    public A() {}

    //抽象方法
    public abstract void showInfo();

    public void method01() {
        System.out.println("A类中的method01方法");
    }
}
