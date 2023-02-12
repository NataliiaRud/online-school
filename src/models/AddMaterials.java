package models;

public class AddMaterials extends Base {
    private String materials;
    private static int counter;
    public AddMaterials(Integer id, String materials) {
        super(id);
        this.materials = materials;
        counter++;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        AddMaterials.counter = counter;
    }
}

