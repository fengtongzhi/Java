package base01.interface_stu_demo;

public class ClassDataInterImpl1 implements ClassDataInter {

    private Student[] students;

    public ClassDataInterImpl1(){}

    public ClassDataInterImpl1(Student[] students) {
        this.students = students;
    }


    @Override
    public void printAllStudentInfos() {
        System.out.println("全班学生信息如下：");
        for (Student student : students) {
            System.out.println(student.getName()+", "+student.getGender()+", "+student.getScore());
        }
    }

    @Override
    public void printAverageScore() {
        double totalScore = 0.0;
        for (Student student : students) {
            totalScore += student.getScore();
        }
        double averageScore = totalScore / students.length;
        System.out.printf("全班平均成绩为: %.2f%n", averageScore);
    }
}
