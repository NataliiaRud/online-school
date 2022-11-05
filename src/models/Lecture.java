package models;

public class Lecture {
    private static int id;
    private String name;
    private Teacher teacher;
    private Students student;
private HomeAssignment homeAssignment;
private AddMaterials addMaterials;

public static int lectureCounter;
    private int courseId;

    public Lecture(int id, String name, Teacher teacher, Students student, HomeAssignment homeAssignment, AddMaterials addMaterials, int courseId) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.student = student;
        this.homeAssignment = homeAssignment;
        this.addMaterials = addMaterials;
        this.courseId = courseId;
        lectureCounter++;
    }
    public int getCourseId() {
        return this.courseId;
    }

}
