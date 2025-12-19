package base01.class_demo;

public class SmartHomeControl {

    private static final SmartHomeControl smartHomeControl=new SmartHomeControl();

    private SmartHomeControl(){}

    public static SmartHomeControl getInstance(){
        return smartHomeControl;
    }

    public void control(JD jd){
        System.out.println("正在操作的设备: "+jd.getName()
                            +"状态:"+jd.isStatus());
        jd.press();
        System.out.println("设备新状态:"+jd.isStatus());
    }

    public void ShowInfo(){
        System.out.println("请选择您要操作的设备:");
        System.out.println("1.电视");
        System.out.println("2.空调");
        System.out.println("3.洗衣机");
        System.out.println("4.台灯");
        System.out.println("0.退出系统");
    }

}
