package base01.interface_demo;

public class Test {
    public static void main(String[] args) {

        //类可以有更多角色
        People p=new Student();
        Driver d=new Student();
        BoyFriends bf =new Student();

        //接口可以实现面相接口编程，利于解耦合
        Driver driver = new Student();
        BoyFriends boyFriends=new Teacher();
    }
}

interface Driver{}
interface BoyFriends{}

class People{}
class Student extends People implements Driver, BoyFriends{}

class Teacher extends People implements Driver, BoyFriends{}
