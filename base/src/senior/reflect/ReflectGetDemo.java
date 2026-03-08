package senior.reflect;

public class ReflectGetDemo {
    public static void main(String[] args) {
        //掌握反射的第一步操作：获取Class类的对象
        //方式1：类.class
        Class<Student> c1=Student.class;
        System.out.println(c1);

        //方式2：Class.forName("全类名")
        try {
            Class<?> c2=Class.forName("senior.reflect.Student");
            System.out.println(c2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //方式3：对象.getClass()
        Student s=new Student();
        Class<? extends Student> c3=s.getClass();
        System.out.println(c3);
    }
}
