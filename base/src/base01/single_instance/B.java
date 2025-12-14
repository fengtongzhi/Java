package base01.single_instance;

//懒汉式单例设计类
public class B {

    //声明类的唯一实例，使用private修饰
    private static B b;

    //私有化构造方法，防止外部创建对象
    private B() {
    }

    //提供公共的静态方法，获取类的唯一实例
    public static B getInstance() {
        if (b == null) {
            b=new B();
        }
        return b;
    }
}
