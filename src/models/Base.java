package models;
import java.util.Objects;

public abstract class Base {
    private Integer id;
    private String name;


    //one-var constructor
    protected Base(Integer id) {
        this.id = id;
    }
    //two-vars constructor
    protected Base(Integer id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}
