package advanced.exception;

public class ExceptionDemo1 {
    public static void main(String[] args) {

        try {
            System.out.println(div(10, 0));
        }catch(Exception e){
            System.out.println("捕获到异常："+e.getMessage());
        }
    }

    public static int div(int a,int b) throws Exception {
        if(b==0){
            throw new Exception("除数为零异常");
        }
        int c=a/b;
        return c;
    }
}
