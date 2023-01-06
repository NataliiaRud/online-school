package models;

public class Base {
    private int id;
    private String name;


    //one-var constructor
    public Base(int id) {
        this.id = id;
    }
    //two-vars constructor
    public Base(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}