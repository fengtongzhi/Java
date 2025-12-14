package base01.polymorphsm;

public class Wolf extends Animal{

    @Override
    public void run(){
        System.out.println("狼飞快地跑");
    }

    public void eatSheep(){
        System.out.println("狼吃羊");
    }
}
