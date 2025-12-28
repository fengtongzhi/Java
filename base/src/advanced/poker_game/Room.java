package advanced.poker_game;

import java.util.*;

public class Room {

    private final List<Card> cards = new ArrayList<>();

    //实例代码块，初始化牌类
    {
        String[] colors = {"红桃", "黑桃", "方片", "梅花"};
        String[] sizes = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A","2"};
        int num=0;
        for (String size : sizes) {
            num++;
            for (String color : colors) {
                cards.add(new Card(size, color,num));
            }
        }
        //添加大小王
        cards.add(new Card("大王", "",++num));
        cards.add(new Card("小王", "",++num));

    }

    public void start() {
        //洗牌
        Collections.shuffle(cards);
        System.out.println(cards);

        //发牌
        Map<String,List<Card>> map =  new HashMap<>();

        List<Card> lhc=new ArrayList<>();
        map.put("令狐冲",lhc);

        List<Card> lhb=new ArrayList<>();
        map.put("令狐白",lhb);

        List<Card> lhz=new ArrayList<>();
        map.put("令狐紫",lhz);

        for (int i = 0; i < 51; i++) {
            Card card = cards.get(i);
            if(i%3==0){
                lhc.add(card);
            }else if(i%3==1){
                lhb.add(card);
            }else{
                lhz.add(card);
            }
        }

        //拿到最后三张底牌
        List<Card> diPai=cards.stream().skip(51).toList();

        //抢地主
        lhb.addAll(diPai);

        //对牌排序
        Comparator<Card> comparator= (c1, c2) -> c2.getNum()-c1.getNum();
        lhc.sort(comparator);
        lhb.sort(comparator);
        lhz.sort(comparator);

        //看牌
        for(Map.Entry<String,List<Card>> entry : map.entrySet()){
            String name=entry.getKey();
            List<Card> handCards=entry.getValue();
            System.out.println(name+"的牌是："+handCards);
        }
    }
}
