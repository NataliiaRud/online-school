package ua.study.school.models;

import ua.study.school.exceptions.ValidationExceptions;
import ua.study.school.utility.LogService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Person extends Base {
    private static final LogService logService = new LogService(Person.class.getName());

    private static final Set<String> emails = new HashSet<>();

    private int courseId;
    private final Role role;
    private String firstName;
    private String lastName;


    private String phone;
    private String email;
    private static int counter;

    public Person(Role role) {
        super(0);
        this.role = role;
    }

    protected Person(Integer id, String firstName, String lastName, Role role, int courseId, String phone, String email) {

        super(id);
        this.courseId = courseId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.counter++;
    }
    public static Person createPerson(Integer id, String firstName, String lastName, Role role, int courseId, String phone, String email) {
        if (emailCheck(email)) {
            return null;
        }

        if (emails.contains(email)) {
            return null;
        }
        emails.add(email);

        if (phoneCheck(phone)) {
            return null;
        }
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
        try {
            if (email.indexOf('@') == -1) {
                throw new ValidationExceptions("Wrong email format");
            }
        } catch(ValidationExceptions e) {
            logService.error("Email validation error", e);
        }
        if(email.indexOf('@')== -1) {
            System.out.println("Email error");
            return true;
        } else {
            return false;
        }
    }

    public static boolean phoneCheck(String phone) {
        try {
            if (phone.length() != 11)
                throw new ValidationExceptions("Wrong phone number");
        }
        catch(ValidationExceptions e) {
            logService.error("Email phone error", e);

        }
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



    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Person.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return courseId == person.courseId && role == person.role && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, role, firstName, lastName);
    }




}
