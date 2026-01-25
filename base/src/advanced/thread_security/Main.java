package advanced.thread_security;

public class Main {
    public static void main(String[] args) {
        Card account = new Card("111", 1000);

        new DrawThread("Alice", account).start();
        new DrawThread("Bob", account).start();
    }
}
