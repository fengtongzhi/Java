package advanced.set_map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
    public static void main(String[] args) {

        //Map:无序，key不重复，无索引
//        Map<String,Integer> map = new HashMap<>();
        //LinkedHashMap有序
//        Map<String,Integer> map = new LinkedHashMap<>();
        //TreeMap排序
        Map<String, Integer> map = new TreeMap<>();
        map.put("Alice", 30);
        map.put("Bob", 25);
        map.put("Charlie", 35);
        map.put("Daniel", 40);
        map.put("Alice", 32); // 更新Alice的年龄

        System.out.println(map);

        for (String name : map.keySet()) {
            Integer age = map.get(name);
            System.out.println(name + " is " + age + " years old.");
        }

        System.out.println(map.values());
        System.out.println(map.keySet());
        System.out.println(map.get("Alice"));
    }
}
