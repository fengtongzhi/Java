package advanced.redpacketDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadTest {
    public static void main(String[] args) {
        List<Integer> redPacketList = getRedPacket();
        for (int i = 0; i < 100; i++) {
            EmployeeGetRed employeeGetRed = new EmployeeGetRed(redPacketList, "员工" + (i + 1));
            employeeGetRed.start();
        }
    }

    public static List<Integer> getRedPacket(){
        Random r=new Random();
        List<Integer> RedPacketList=new ArrayList<>();
        for (int i = 0; i < 160; i++) {
            RedPacketList.add(r.nextInt(30)+1);
        }
        for (int i = 0; i < 40; i++) {
            RedPacketList.add(r.nextInt(30,100));
        }

        return RedPacketList;
    }
}
