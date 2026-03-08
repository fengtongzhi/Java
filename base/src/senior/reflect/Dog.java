package senior.reflect;

import lombok.Data;

@Data
public class Dog {
    private String name;
    private int age;
    private String color;

    private Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void bark() {
        System.out.println("Woof! Woof!");
    }

    private void eat(String food) {
        System.out.println( "Dog is eating " + food);
    }
}
