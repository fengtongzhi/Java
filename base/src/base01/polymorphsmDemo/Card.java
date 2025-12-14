package base01.polymorphsmDemo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String carId;
    private String name;
    private String phone;
    private double balance;
}
