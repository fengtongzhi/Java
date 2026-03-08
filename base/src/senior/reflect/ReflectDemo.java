package senior.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectDemo {
    @Test
    public void getClassInfo(){
        Class<Student> c=Student.class;
        System.out.println("类的名称："+c.getName());
        System.out.println("简单类名："+c.getSimpleName());
    }

    @Test
    public void getConstructorInfo(){
        Class<?> c= Dog.class;

        System.out.println("所有的构造方法：");
        Constructor<?>[] cons=c.getDeclaredConstructors();
        for(Constructor<?> con:cons){
            System.out.println(con.getName()+"  参数个数："+con.getParameterCount());
        }

        System.out.println("获取指定的构造方法：");
        try {
            Constructor<?> con=c.getDeclaredConstructor(String.class,int.class);
            System.out.println(con.getName()+"  参数个数："+con.getParameterCount());

            //获取构造器作用：创建对象
            con.setAccessible(true);//忽略访问权限修饰符的安全检查
            Object obj=con.newInstance("Buddy",3);
            System.out.println(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getFieldInfo() {
        //获取类对象
        Class<?> c=Dog.class;

        //获取所有的成员变量
        System.out.println("所有的成员变量：");
        Field[] fields=c.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field.getName()+"  "+field.getType().getSimpleName());
        }

        //获取指定的成员变量
        System.out.println("获取指定的成员变量：");
        try {
            Field field=c.getDeclaredField("color");
            System.out.println(field.getName()+"  "+field.getType().getSimpleName());

            Dog dog=new Dog("Buddy",3);
            field.setAccessible(true);
            field.set(dog,"White");
            //取值
            Object value=field.get(dog);
            System.out.println("成员变量的值："+value+"  "+value.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMethodInfo(){
        //获取类对象
        Class<?> c=Dog.class;

        System.out.println("所有的方法：");
        //获取所有的方法
        Method[] methods=c.getDeclaredMethods();
        for(Method method:methods){
            System.out.println(method.getName()+"  参数个数："+method.getParameterCount());
        }

        System.out.println("获取指定的方法：");
        try {
            Method method=c.getDeclaredMethod("eat",String.class);
            System.out.println(method.getName()+"  参数个数："+method.getParameterCount());

            //调用方法
            Dog dog=new Dog("Buddy",3);
            method.setAccessible(true);
            method.invoke(dog,"Bone");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reflectIgnoreE(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");

        Class<?> c=list.getClass();
        try {
            Method method=c.getDeclaredMethod("add",Object.class);
            method.setAccessible(true);
            method.invoke(list,123);
            method.invoke(list,true);

            for (Object obj:list){
                System.out.println(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
