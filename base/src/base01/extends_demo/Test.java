package base01.extends_demo;

public class Test {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setName("Alice");
        teacher.setAge(30);
        teacher.setSkill("Mathematics");

        System.out.println("Teacher Name: " + teacher.getName());
        System.out.println("Teacher Age: " + teacher.getAge());
        System.out.println("Teacher Skill: " + teacher.getSkill());

    }
}
