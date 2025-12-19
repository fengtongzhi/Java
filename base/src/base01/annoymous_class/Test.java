package base01.annoymous_class;

public class Test {
    public static void main(String[] args) {
        // 使用匿名内部类实现接口
        Animal a = new Animal() {
            @Override
            public void eat() {
                System.out.println("猫吃鱼");
            }
        };

        a.eat();
    }
}

//class Cat extends Animal {
//    @Override
//    public void eat() {
//        System.out.println("猫吃鱼");
//    }
//}
