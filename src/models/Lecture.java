package models;


public class Lecture extends Base {

    private Teacher teacher;
    private Students student;
    private int courseId;
    private int personId;
    private String description;
    private static int counter;


    public Lecture(int id, String name, Teacher teacher, Students student, int courseId, int personId, String description) {
        super(id, name);
        this.teacher = teacher;
        this.student = student;
        this.courseId = courseId;
        this.personId = personId;

        this.description = description;
counter++;

    }

    public int getCourseId() {
        return this.courseId;
    }

    public static Lecture createLecture(int id, String name, Teacher teacher, Students student, int courseId, int personId, String description) {
        return new Lecture(id, name, teacher, student, courseId, personId, description);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", teacher=" + this.teacher.getLastName() +
                ", student=" + this.student.getLastName() +
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


