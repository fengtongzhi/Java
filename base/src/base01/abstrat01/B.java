package base01.abstrat01;

public class B extends A {
    //一个类继承了抽象类，就必须实现抽象类中的所有抽象方法，否则这个类也必须声明为抽象类
    public B(String name, int age) {
        super(name, age);
    }

    //实现抽象方法
    @Override
    public void showInfo() {
        System.out.println("B类中的showInfo方法");
    }
}
