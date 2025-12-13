package base01.javabean;

public class StudentOperator {

    public void printAvgScore(Student stu){
        double avg=(stu.getChinese()+stu.getMath())/2;
        System.out.println("Average Score of "+stu.getName()+" is: "+avg);
    }

    public void printTotalScore(Student stu){
        double total=stu.getChinese()+stu.getMath();
        System.out.println("Total Score of "+stu.getName()+" is: "+total);
    }
}
