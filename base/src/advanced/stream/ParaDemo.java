package advanced.stream;

public class ParaDemo {
    public static void main(String[] args) {
        sum();//也可以空参数
        sum(10);
        sum(10,20,30);//可变参数个数不限
        sum(new int[]{1,2,3,4,5,6,7,8,9,10});//数组也可以
    }

    public static void sum(int...nums){
        //实际上内部是nums是一个数组
        int sum=0;
        for(int n:nums){
            sum+=n;
        }
        System.out.println("Sum="+sum);
    }
}
