package base01.polymorphsm_demo;

public class CardOperator {
    private Card card;

    public CardOperator(){
    }

    public CardOperator(Card card) {
        this.card = card;
    }

    public void saveMoney(double money){
        double balance=card.getBalance()+money;
        card.setBalance(balance);
    }

    public void consumeMoney(double money){
        double balance = 0.0;
        if(card instanceof CardSVIP){
            balance = card.getBalance()-money*0.8;
            if(balance<0) {;
                System.out.println("余额不足，无法完成本次消费");
                return;
            }
            if(money>=200){
                System.out.println("您本次消费满200，提供免费洗车票一张");
            }
        }else if(card instanceof CardVIP){
            balance = card.getBalance()-money*0.9;
            if(balance<0) {;
                System.out.println("余额不足，无法完成本次消费");
                return;
            }
        }else{
            balance = card.getBalance()-money;
            if(balance<0) {;
                System.out.println("余额不足，无法完成本次消费");
                return;
            }
        }

        card.setBalance(balance);
        System.out.println("您当前余额为："+balance);
    }

}
