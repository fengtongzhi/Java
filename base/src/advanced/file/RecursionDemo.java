package advanced.file;

public class RecursionDemo {
    public static void main(String[] args) {
        System.out.println(func(5));

        System.out.println(func2(1));
    }

    private static int func(int i) {
        if(i==1){
            return 1;
        }
        return i*func(i-1);
    }

    private static int func2(int i) {
        if(i==10){
            return 1;
        }
        return 2*(func2(i+1)+1);
    }
}
