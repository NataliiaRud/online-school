package models;

public class School extends Base {
    private static int counter;
    public School(Integer id, String name) {
        super(id, name);
        counter++;
    }
    public School(Integer id) {
        super(id);
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        School.counter = counter;
    }

}
