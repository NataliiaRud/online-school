package models;


public class Lecture extends Base {

    private Teacher teacher;
    private Students student;
    private int courseId;
    private static int counter;

    public Lecture(int id, String name, Teacher teacher, Students student, int courseId) {
        super(id, name);
        this.teacher = teacher;
       this.student = student;
       this.courseId = courseId;
counter++;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public static Lecture createLecture(int id, String name, Teacher teacher, Students student, int courseId) {
        return new Lecture(id, name, teacher, student, courseId);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", teacher=" + teacher +
                ", student=" + student +
                ", courseId=" + courseId +
                '}';
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Lecture.counter = counter;
    }
}

