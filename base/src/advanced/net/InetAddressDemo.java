package advanced.net;

import java.net.InetAddress;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            InetAddress it=InetAddress.getLocalHost();
            System.out.println(it);

            InetAddress ia=InetAddress.getByName("www.baidu.com");
            System.out.println(ia);

            System.out.println(ia.isReachable(5000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
