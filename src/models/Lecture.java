package models;
import java.util.Scanner;

public class Lecture {
    private int id;
    private String name;
    private Teacher teacher;
    private Students student;

    public static int lectureCounter;
    private int courseId;

    public Lecture(int id, String name, Teacher teacher, Students student, int courseId) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.student = student;
        this.courseId = courseId;
        lectureCounter++;
    }
    public int getCourseId() {
        return this.courseId;
    }

    public static Lecture createLecture(int id, String name, Teacher teacher, Students student, int courseId) {
        return new Lecture(id, name, teacher, student, courseId);
    }

    public int getLectureId() {
        return this.id;
    }

}

