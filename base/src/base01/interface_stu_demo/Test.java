package base01.interface_stu_demo;

public class Test {
    public static void main(String[] args) {
        Student[] allStudents =new Student[10];
        allStudents[0]=new Student("张三",'男',85.5);
        allStudents[1]=new Student("李四",'女',92.0);
        allStudents[2]=new Student("王五",'男',76.5);
        allStudents[3]=new Student("赵六",'女',88.0);
        allStudents[4]=new Student("钱七",'男',95.5);
        allStudents[5]=new Student("孙八",'女',67.0);
        allStudents[6]=new Student("周九",'男',73.5);
        allStudents[7]=new Student("吴十",'女',81.0);
        allStudents[8]=new Student("郑十一",'男',89.5);
        allStudents[9]=new Student("冯十二",'女',94.0);

        //提供两套业务实现方案，支持灵活切换，面向接口编程
        ClassDataInter cdi=new ClassDataInterImpl2(allStudents);
        cdi.printAllStudentInfos();
        cdi.printAverageScore();
    }
}
