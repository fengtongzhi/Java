package advanced.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("苹果");
        list.add("香蕉");
        list.add("橘子");

        System.out.println("初始列表: " + list);

        // 在索引1处添加元素
        list.add(1, "葡萄");
        System.out.println("添加葡萄后: " + list);

        // 获取索引2处的元素
        String fruitAtIndex2 = list.get(2);
        System.out.println("索引2处的元素: " + fruitAtIndex2);

        // 修改索引3处的元素
        String set_element=list.set(3, "西瓜");
        System.out.println("修改索引3后的列表: " + set_element);

        // 删除索引0处的元素
        String del_element=list.remove(0);
        System.out.println("删除索引0后的列表: " + del_element);

        System.out.println("最终列表: " + list);

        Iterator<String> it=list.iterator();
        while(it.hasNext()){
            String fruit=it.next();
            System.out.println("元素: "+fruit);
        }

        for(String fruit:list){
            System.out.println(fruit);
        }

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        list.forEach(s->System.out.println(s));

        System.out.println("list = " + list);
    }
}
