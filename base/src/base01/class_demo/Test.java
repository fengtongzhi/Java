package base01.class_demo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        JD[] jds =new JD[4];
        jds[0]=new WashMachine("洗衣机",false);
        jds[1]=new Lamp("台灯",true);
        jds[2]=new Air("空调",false);
        jds[3]=new JD("电视机",true);

        Scanner in=new Scanner(System.in);

        SmartHomeControl shc=SmartHomeControl.getInstance();
        int jds_len=jds.length;
        System.out.println("欢迎使用智能家居控制系统");
        while(true){
            shc.ShowInfo();
            int choice=in.nextInt();
            if(choice==0){
                System.out.println("退出系统，欢迎下次使用");
                break;
            }else if(choice>0 && choice<=jds_len){
                shc.control(jds[choice-1]);
            }else{
                System.out.println("输入有误，请重新输入");
            }
            System.out.println();

        }

        in.close();
    }
}
