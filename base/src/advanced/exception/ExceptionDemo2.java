package advanced.exception;

public class ExceptionDemo2 {
    public static void main(String[] args) {
        try {
            saveAge(200);
        }catch (AgeIllegalException e){
            System.out.println("捕获到年龄不合法异常："+e.getMessage());
        }
    }

    public static void saveAge(int age) throws AgeIllegalException {
        if(age<0 || age>150){
            throw new AgeIllegalException("年龄不合法："+age);
        }else{
            System.out.println("年龄保存成功："+age);
        }
    }
}
