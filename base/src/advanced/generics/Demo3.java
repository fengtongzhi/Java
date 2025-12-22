package advanced.generics;

public class Demo3 {
    public static void main(String[] args) {
        //自定义泛型方法
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"Hello", "Generics", "Method"};
        showArray(intArray);
        showArray(strArray);

        Integer maxInt = getEqual(10, 20);
        System.out.println("Max Integer: " + maxInt);
        String maxStr = getEqual("Hello", "Hello");
        System.out.println("Max String: " + maxStr);
    }

    public static <T> void showArray(T[] array){
        for (T t : array) {
            System.out.println(t);
        }
    }

    public static <T> T getEqual(T a, T b){
        if (a.equals(b)) {
            return a;
        }
        return null;
    }
}
