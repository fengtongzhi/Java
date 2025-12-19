package base01.polymorphsm;

public class Tortoise extends Animal{

    @Override
    public void run(){
        System.out.println("乌龟慢慢地跑");
    }

    public void eat(){
        System.out.println("乌龟吃菜");
    }
}
