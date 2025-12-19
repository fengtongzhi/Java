package base01.javabean;

public class Test {
    public static void main(String[] args) {
        Student s1=new Student("Alice",60,90.0);
        Student s2=new Student("Bob",70,80);

        StudentOperator operator=new StudentOperator();
        operator.printAvgScore(s1);
        operator.printTotalScore(s2);
    }
}
