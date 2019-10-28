package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_H2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public  class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String mark;
    private String model;
    private int age;
    private float weight;
    private int price;
    private String dateProduction;
    private String description;

    public Bicycle() {
    }

    public Bicycle(String name, String mark, String model, int age, float weight, int price, String dateProduction, String description) {
        this.name = name;
        this.mark = mark;
        this.model = model;
        this.age = age;
        this.weight = weight;
        this.price = price;
        this.dateProduction = dateProduction;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateProduction() {
        return dateProduction;
    }

    public void setDateProduction(String dateProduction) {
        this.dateProduction = dateProduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", price=" + price +
                ", dateProduction=" + dateProduction +
                ", description='" + description + '\'' +
                '}';
    }
}
