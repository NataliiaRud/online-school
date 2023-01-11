package models;

public abstract class Base {
    private int id;
    private String name;


    //one-var constructor
    protected Base(int id) {
        this.id = id;
    }
    //two-vars constructor
    protected Base(int id, String name) {
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
