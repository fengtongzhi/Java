package base01.interface_stu_demo;

public class ClassDataInterImpl2 implements ClassDataInter{

    private Student[] students;

    public ClassDataInterImpl2(){}

    public ClassDataInterImpl2(Student[] students) {
        this.students = students;
    }

    @Override
    public void printAllStudentInfos() {
        int maleNum=0;
        int allNum=students.length;

        System.out.println("全班学生信息如下：");

        for (Student student : students) {
            System.out.println(student.getName()+", "+student.getGender()+", "+student.getScore());
            if(student.getGender()=='男') {
                maleNum++;
            }
        }
        System.out.printf("全班共有学生%d人，其中男生%d人，女生%d人%n", allNum, maleNum, (allNum - maleNum));
    }

    @Override
    public void printAverageScore() {
        //打印平均成绩，消除最高分与最低分
        double totalScore = 0.0;
        double maxScore = students[0].getScore();
        double minScore = students[0].getScore();

        for (Student student : students) {
            double score = student.getScore();
            totalScore += score;
            if(score > maxScore){
                maxScore = score;
            }
            if(score < minScore){
                minScore = score;
            }
        }

        double adjustedTotal = totalScore - maxScore - minScore;
        double averageScore = adjustedTotal / (students.length - 2);
        System.out.printf("去除最高分和最低分后的平均成绩为: %.2f%n", averageScore);
    }
}
