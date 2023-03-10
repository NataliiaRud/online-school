package ua.study.school.models;


import java.io.Serializable;
import java.util.Date;

public class Lecture extends Base implements Serializable {
    private static final long serialVersionUID = 2394135373501585452L;

    public Lecture() {
        super(0);
    }

    private Teacher teacher;
    private Students student;
    private int courseId;
    private int personId;
    private String description;
    private Date date = new Date();

    private HomeAssignment[] homeAssignments;
    private AddMaterials[] addMaterials;
    private static int counter;


    public Lecture(Integer id, String name, Teacher teacher, Students student, int courseId, int personId, String description, HomeAssignment[] homeAssignments, AddMaterials[] addMaterials) {
        super(id, name);
        this.teacher = teacher;
        this.student = student;
        this.courseId = courseId;
        this.personId = personId;
        this.description = description;
        this.homeAssignments = homeAssignments;
        this.addMaterials = addMaterials;
        counter++;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public static Lecture createLecture(Integer id, String name, Teacher teacher, Students student, Integer courseId, Integer personId, String description, HomeAssignment[] homeAssignments, AddMaterials[] addMaterials) {
        return new Lecture(id, name, teacher, student, courseId, personId, description, homeAssignments, addMaterials);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public HomeAssignment[] getHomeAssignments() {
        return homeAssignments;
    }

    public void setHomeAssignments(HomeAssignment[] homeAssignments) {
        this.homeAssignments = homeAssignments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Lecture.counter = counter;
    }


    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", teacher=" + this.teacher.getLastName() +
                ", student=" + this.student.getLastName() +
                ", courseId=" + courseId +
                ", personId=" + personId +
                ", description=" + description +
                ", addMaterials=" + addMaterials +
                '}';
    }
}


