package base01.extendsDemo;

public class TestDemo1 {
    public static void main(String[] args) {
        Cat cat=new Cat();
        cat.cry();
    }
}

class Cat extends Animals{

    @Override
    public void cry(){
        System.out.println("喵喵叫");
        super.cry();
    }
}

class Animals {
    public void cry(){
        System.out.println("动物叫");
    }
}
