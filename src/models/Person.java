package models;

public abstract class Person extends Base {
private int courseId;
private final Role role;
private String firstName;
private String lastName;

    private String phone;
    private String email;
private static int counter;

    protected Person(int id, String firstName, String lastName, Role role, int courseId, String phone, String email) {
        super(id);
        this.courseId = courseId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.counter++;
    }
    public static Person createPerson(int id, String firstName, String lastName, Role role, int courseId, String phone, String email) {
        if (emailCheck(email)) {return null;}
        if (phoneCheck(phone)) {return null;}
        if (role == Role.TEACHER) {
            return new Teacher(id, firstName, lastName, role, courseId, phone, email);
        } else if (role == Role.STUDENT) {
            return new Students(id, firstName, lastName, role, courseId, phone, email);
        } else {
            System.out.println("You've entered the wrong role");
            return null;
        }
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean emailCheck(String email) {
        if(email.indexOf('@')== -1) {
            System.out.println("Email error");
            return true;
        } else {
            return false;
        }
    }

    public static boolean phoneCheck(String phone) {
        boolean areNumbers = false;
        for (int i =0; i<phone.length(); i++) {
            areNumbers = Character.isDigit(phone.charAt(i));
            if (!areNumbers) {
                return true;
            }
        }
        if (phone.length() != 11) {
            System.out.println("Phone error");
            return true;
        } else {
            return false;
        }
    }


}
