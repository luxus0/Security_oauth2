package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_JDBC;


public class People {

    private int id;

    private String name;

    private String surname;

    private int height;

    private int wage;

    private int age;

    private String email;
public People(){}
    public People(int id, String name, String surname, int height, int wage, int age, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.wage = wage;
        this.age = age;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
