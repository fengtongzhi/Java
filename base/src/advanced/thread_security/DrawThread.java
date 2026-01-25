package advanced.thread_security;

public class DrawThread extends Thread {
    private Card card;
    public DrawThread(String name,Card card) {
        super(name);
        this.card = card;
    }
    @Override
    public void run() {
        card.drawMoney(800);
    }
}
