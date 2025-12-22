package advanced.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class Demo1 {
    public static void main(String[] args) {
        Collection<String> names=new ArrayList<>();

        names.add("张三");
        names.add("李四");
        names.add("王五");
        names.add("赵六");
        System.out.println(names);

        //获取迭代器对象 Iterator
        Iterator<String> iterator = names.iterator();
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());

        while(iterator.hasNext()){
            String name = iterator.next();
            System.out.println(name);
        }

        System.out.println("=======================");

        //for增强循环（for-each）
        for(String name:names){
            System.out.println(name);
        }

        System.out.println("=======================");
        //Lambda表达式,for-each方法
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        System.out.println("=======================");

        //简化Lambda表达式
        names.forEach(s -> System.out.println(s));
    }
}
