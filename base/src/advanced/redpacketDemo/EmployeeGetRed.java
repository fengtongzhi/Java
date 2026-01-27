package advanced.redpacketDemo;

import java.util.List;

public class EmployeeGetRed extends Thread{
    private List<Integer> redPacket;

    public EmployeeGetRed(List<Integer> redPacket, String name) {
        super(name);
        this.redPacket = redPacket;
    }

    @Override
    public void run() {
        while (true){
            synchronized (redPacket){
                if (redPacket.size()==0){
                    break;
                }
                Integer money = redPacket.remove((int) (Math.random()*redPacket.size()));
                System.out.println(getName()+"抢到了"+money+"元红包");
            }
        }
    }
}
