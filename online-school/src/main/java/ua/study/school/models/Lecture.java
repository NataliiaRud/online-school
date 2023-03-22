package ua.study.school.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Lecture extends Base implements Serializable {
    @Serial
    private static final long serialVersionUID = 2394135373501585452L;

    private int teacherId;
    private int courseId;
    private String description;
    private Date lectureDate = new Date();

    private HomeAssignment[] homeAssignments;
    private AdditionalMaterial[] addMaterials;
    private static int counter;


    public Lecture(Integer id, String name, String description, int teacherId, int courseId, HomeAssignment[] homeAssignments, AdditionalMaterial[] addMaterials) {
        super(id, name);
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.description = description;
        this.homeAssignments = homeAssignments;
        this.addMaterials = addMaterials;
        counter++;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public static Lecture createLecture(Integer id, String name, String description, int teacherId, Integer courseId, HomeAssignment[] homeAssignments, AdditionalMaterial[] addMaterials) {
        return new Lecture(id, name, description, teacherId, courseId, homeAssignments, addMaterials);
    }

    public int getTeacherId() {
        return teacherId;
    }

    public HomeAssignment[] getHomeAssignments() {
        return homeAssignments;
    }

    public void setHomeAssignments(HomeAssignment[] homeAssignments) {
        this.homeAssignments = homeAssignments;
    }

    public Date getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(Date lectureDate) {
        this.lectureDate = lectureDate;
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
                ", description=" + description +
                ", teacherId=" + this.teacherId +
                ", courseId=" + courseId +
                ", addMaterials=" + addMaterials +
                '}';
    }
}


