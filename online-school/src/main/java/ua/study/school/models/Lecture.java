package ua.study.school.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "lecture")
public class Lecture extends Base implements Serializable {
    @Serial
    private static final long serialVersionUID = 2394135373501585452L;

    private int teacherId;
    private int courseId;
    private String description;
    private Date lectureDate = new Date();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    private Collection<HomeAssignment> homeAssignments;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lecture")
    private Collection<AdditionalMaterial> addMaterials;
    private static int counter;

    public Lecture() {
        super(0);
    }

    public Lecture(Integer id, String name, String description, int teacherId, int courseId,
                   Collection<HomeAssignment> homeAssignments, Collection<AdditionalMaterial> addMaterials) {
        super(id, name);
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.description = description;
        this.homeAssignments = homeAssignments;
        this.addMaterials = addMaterials;
        counter++;
    }

    public static Lecture createLecture(Integer id, String name, String description, int teacherId, Integer courseId,
                                        Collection<HomeAssignment> homeAssignments, Collection<AdditionalMaterial> addMaterials) {
        return new Lecture(id, name, description, teacherId, courseId, homeAssignments, addMaterials);
    }

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    @Column(name = "name")
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Column(name = "course_id")
    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Column(name = "teacher_id")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "lecture_date")
    public Date getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(Date lectureDate) {
        this.lectureDate = lectureDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lecture")
    public Collection<HomeAssignment> getHomeAssignments() {
        return homeAssignments;
    }

    public void setHomeAssignments(Collection<HomeAssignment> homeAssignments) {
        this.homeAssignments = homeAssignments;
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


