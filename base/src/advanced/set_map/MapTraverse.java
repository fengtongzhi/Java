package advanced.set_map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public class MapTraverse {
    public static void main(String[] args) {

        Map<String, Integer> map1 = new HashMap<>();

        map1.put("Alice", 30);
        map1.put("Bob", 25);
        map1.put("Charlie", 35);
        map1.put("Daniel", 40);

        //键找值
        Set<String> keys=map1.keySet();
        for(String s:keys){
            Integer value=map1.get(s);
            System.out.println(s+"->"+value);
        }

        System.out.println("--------------------------------------");

        //键值对找值
        Set<Map.Entry<String,Integer>> entries=map1.entrySet();
        for(Map.Entry<String,Integer> entry:entries){
            String key=entry.getKey();
            Integer value=entry.getValue();
            System.out.println(key+"->"+value);
        }

        System.out.println("-------------------------------------");

        //Lambda
        map1.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s+"->"+integer);
            }
        });

        map1.forEach((k,v)->{System.out.println(k+"->"+v);});
    }
}
