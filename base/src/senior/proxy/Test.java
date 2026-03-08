package senior.proxy;

public class Test {
    public static void main(String[] args) {
        Star star = new Star("章若楠");
        Performance proxy = ProxyUtil.creatProxy(star);
        proxy.sing("《天外来物》");
        String res = proxy.dance();
        System.out.println("舞蹈结束，明星说：" + res);
    }
}
