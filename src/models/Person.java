package models;

public abstract class Person extends Base {
private int courseId;
private final Role role;
private String firstName;
private String lastName;
private static int counter;

    public Person(int id, String firstName, String lastName, Role role, int courseId) {
        super(id);
        this.courseId = courseId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.counter++;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Person.counter = counter;
    }
}
