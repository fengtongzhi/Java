package base01.polymorphsm;

public class Test {
    public static void main(String[] args) {
        Animal w=new Wolf();
        Animal t=new Tortoise();

//        w.eatSheep(); // This line will cause a runtime error

        Wolf w2=(Wolf)w;
        go(w2);

//        Tortoise t2=(Tortoise)w;//编译阶段不会报错,运行阶段会报错，用instanceof进行判断
        // t2.run(); // This line will cause a runtime error

        go(w);
        go(t);

        Wolf w1=new Wolf();
        go(w1);

        Tortoise t1=new Tortoise();
        go(t1);
    }

    public static void go(Animal a){
        a.run();
        if(a instanceof Wolf){
            Wolf w=(Wolf)a;
            w.eatSheep();
        }else if(a instanceof Tortoise){
            Tortoise t=(Tortoise)a;
            t.eat();
        }
    }
}
