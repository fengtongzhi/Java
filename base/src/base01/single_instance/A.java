package base01.single_instance;

//单例设计类(饿汉式)
public class A {

    //私有化构造方法，防止外部创建对象
    private A(){
    }

    //静态方法，返回类的唯一实例(进行private封装)
    private static final A a = new A();

    //用final,就不用提供公共的静态方法
    //public static final A a = new A();

    //提供公共的静态方法，获取类的唯一实例
    public static A getInstance() {
        return a;
    }
}
