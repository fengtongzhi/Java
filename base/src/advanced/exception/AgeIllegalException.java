package advanced.exception;


//自定义编译异常类
public class AgeIllegalException extends Exception {
    public AgeIllegalException() {
        super();
    }

    public AgeIllegalException(String message) {
        super(message);
    }

}
