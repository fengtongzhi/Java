package advanced.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("张无忌");
        list.add("赵敏");
        list.add("周芷若");
        list.add("小昭");
        list.add("殷离");
        list.add("张三丰");
        list.add("张强");
        list.add("张翠山");

        //收集到集合中去
        Stream<String> stream = list.stream().filter(s->s.startsWith("张"));
        List<String> strings = stream.collect(Collectors.toList());
        System.out.println(strings);

        //收集到数组中去
        Object[] array=strings.toArray();
        System.out.println(Arrays.toString(array));

        System.out.println("--------传统方式--------");
        func1(list);

        System.out.println("--------Stream方式--------");
        func2(list);

        String[] arr = {"张无忌","赵敏","周芷若","小昭","殷离","张三丰","张强","张翠山"};
        System.out.println("--------Stream方式+数组1--------");
        List<String> list2 = List.of(arr);
        func2(list2);

        System.out.println("--------Stream方式+数组2--------");
        Stream<String> Stream2 = Stream.of(arr);
        Stream2
            .filter(s -> s.startsWith("张"))
            .filter(s -> s.length() == 3)
            .forEach(s -> System.out.println(s));

        System.out.println("-----------------------------------");
        Stream<String> Stream3= Arrays.stream(arr);
        Stream3.filter(s->s.startsWith("张"))
            .filter(s->s.length()==3)
            .forEach(s-> System.out.println(s));
        

        //Stream map方法
        System.out.println("Stream方法");
        List<Double> list1 =new ArrayList<>();
        list1.add(12.3);
        list1.add(45.6);
        list1.add(78.9);
        list1.add(23.4);

        list1.stream().map(s->(s+10)).forEach(System.out::println);

    }

    public static void func1(List<String> list){
        for(String s : list){
            if(s.startsWith("张") && s.length() == 3){
                System.out.println(s);
            }
        }
    }

    public static void func2(List<String> list){
        list.stream()
            .filter(s -> s.startsWith("张"))
            .filter(s -> s.length() == 3)
            .forEach(s -> System.out.println(s));
    }
}
