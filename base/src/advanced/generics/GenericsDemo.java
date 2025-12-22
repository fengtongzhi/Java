package advanced.generics;

//自定义泛型类
public class GenericsDemo {
    public static void main(String[] args) {
        //模拟ArrayList的使用
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hello");
        list.add("Generics");
//        list.add(555); // 编译错误，类型不匹配
    }
}
