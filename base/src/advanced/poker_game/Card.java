package advanced.poker_game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private String size;
    private String color;
    private int num;

    @Override
    public String toString(){
        return color + size;
    }
}
