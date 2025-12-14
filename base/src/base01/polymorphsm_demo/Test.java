package base01.polymorphsm_demo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

        System.out.println("请输入用户充值金额:");
        double balance = in.nextDouble();

        Card c=null;
        CardOperator operator=null;

        if(balance>=5000){
            c=new CardSVIP("1001","张三","13800138000",balance);
            operator=new CardOperator(c);
        }else if(balance>=2000){
            c=new CardVIP("1002","李四","13800138001",balance);
            operator=new CardOperator(c);
        }else{
            c=new CardCommon("1003","王五","13800138002",balance);
            operator=new CardOperator(c);
        }

        while(true){
            showMenu();
            int choice = in.nextInt();
            if(choice==1){
                System.out.print("请输入充值金额:");
                double money = in.nextDouble();
                operator.saveMoney(money);
                System.out.println("充值成功，当前余额为："+c.getBalance());
            }else if(choice==2){
                System.out.print("请输入消费金额:");
                double money = in.nextDouble();
                operator.consumeMoney(money);
            }else if(choice==3){
                System.out.println("当前余额为："+c.getBalance());
            }else if(choice==4){
                System.out.println("感谢使用加油站会员卡系统，欢迎下次光临！");
                break;
            }else{
                System.out.println("输入有误，请重新选择操作菜单(1-4)");
            }
        }

        in.close();
    }

    public static void showMenu(){
        System.out.println("**********欢迎使用加油站会员卡系统**********");
        System.out.println("1.充值");
        System.out.println("2.消费");
        System.out.println("3.查询余额");
        System.out.println("4.退出系统");
        System.out.println("******************************************");
        System.out.print("请选择操作菜单(1-4):");
    }
}
