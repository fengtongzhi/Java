package advanced.set_map;

import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {

        //HashSet无序，不重复，无索引
//        Set<String> set=new HashSet<>();

        //LinkedHashSet有序，不重复，无索引
//        Set<String> set=new LinkedHashSet<>();

        //TreeSet排序，不重复，无索引
        Set<String> set=new TreeSet<>();
        set.add("Java");
        set.add("Python");
        set.add("C++");
        set.add("Java"); //重复元素不会被添加
        for (String language : set) {
            System.out.println(language);
        }
    }
}
