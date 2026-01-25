package advanced.thread_security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String cardId;
    private double balance;

    //同步代码块
//    public void drawMoney(double money) {
//        String threadName = Thread.currentThread().getName();
//        synchronized (this) {
//            if (balance >= money) {
//                System.out.println(threadName + " is drawing " + money);
//                balance -= money;
//                System.out.println(threadName + " completed the withdrawal. Remaining balance: " + balance);
//            } else {
//                System.out.println(threadName + " attempted to withdraw " + money + " but has insufficient funds. Current balance: " + balance);
//            }
//        }
        //同步方法
        public synchronized void drawMoney(double money) {
            String threadName = Thread.currentThread().getName();
            if (balance >= money) {
                System.out.println(threadName + " is drawing " + money);
                balance -= money;
                System.out.println(threadName + " completed the withdrawal. Remaining balance: " + balance);
            } else {
                System.out.println(threadName + " attempted to withdraw " + money + " but has insufficient funds. Current balance: " + balance);
            }
        }
}
