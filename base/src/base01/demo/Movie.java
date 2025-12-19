package base01.demo;

public class Movie {
    private String name;
    private String id;
    private double price;
    private String actor;

    public Movie() {
    }

    public Movie(String name, String id, double price, String actor) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.actor = actor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
