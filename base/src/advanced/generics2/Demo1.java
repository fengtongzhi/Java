package advanced.generics2;

import java.util.ArrayList;

//了解泛型通配符和泛型上下限
public class Demo1 {
    public static void main(String[] args) {
        ArrayList<Xiaomi> xmi=new ArrayList<>();
        xmi.add(new Xiaomi());

        ArrayList<BYD> byd=new ArrayList<>();
        byd.add(new BYD());

        showList1(xmi);
        showList1(byd);

        showList2(xmi);

        ArrayList<Dog> dog=new ArrayList<>();
        dog.add(new Dog());
//        showList3(dog); //编译错误，类型不匹配

        showList4(xmi);
    }

    //通配符
    public static void showList1(ArrayList<?> cars){
        for (Object car : cars) {
            System.out.println(car);
        }
    }

    //泛型方法
    public static <T> void showList2(ArrayList<T> cars){
        for (T car : cars) {
            System.out.println(car);
        }
    }

    //泛型上下限
    public static void showList3(ArrayList<? extends Car> cars){
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    //泛型上下限
    public static <T extends Car> void showList4(ArrayList<T> cars){
        for (T car : cars) {
            System.out.println(car);
        }
    }
}
