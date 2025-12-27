package advanced.set_map;

import java.util.*;

public class MapDemp01 {
    public static void main(String[] args) {
        cacl();
    }

    private static void cacl() {
        List<String> list = new ArrayList<>();
        String[] names={"长城","比亚迪","特斯拉","蔚来","小鹏","理想"};
        Random random = new Random();
        for (int i = 0; i < 80; i++) {
            int index=random.nextInt(names.length);
            list.add(names[index]);
        }
        System.out.println(list);

        Map<String,Integer> map = new HashMap<>();
        for(String name:list){
            if(map.containsKey(name)){
                Integer count=map.get(name);
                map.put(name,count+1);
            }else{
                map.put(name,1);
            }
        }
        System.out.println(map);

        map.forEach((k,v)->{System.out.println(k+"->"+v);});
    }


}
