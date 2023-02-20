package models;


public class Lecture extends Base {

    private Teacher teacher;
    private Students student;
    private int courseId;
    private int personId;
    private String description;

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

    public HomeAssignment[] getHomeAssignments() {
        return homeAssignments;
    }

    public void setHomeAssignments(HomeAssignment[] homeAssignments) {
        this.homeAssignments = homeAssignments;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Lecture.counter = counter;
    }


}


