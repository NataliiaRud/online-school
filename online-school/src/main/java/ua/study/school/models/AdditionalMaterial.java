package ua.study.school.models;

import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "additional_material")
public class AdditionalMaterial extends Base implements Serializable {
    @Serial
    private static final long serialVersionUID = 688325859070780787L;

    private String name;
    private String description;
    private Integer lectureId;
    private ResourceType resourceType;
    private static int counter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Lecture lecture;

    public AdditionalMaterial() {
        super(0);
    }

    public AdditionalMaterial(Integer id, String name, String description, Integer lectureId, ResourceType resourceType) {
        super(id);
        this.name = name;
        this.description = description;
        this.lectureId = lectureId;
        this.resourceType = resourceType;
        counter++;
    }

    public static AdditionalMaterial createAddMaterials(Integer id, String name, String description,
                                                        Integer lectureId, ResourceType resourceType) {
        return new AdditionalMaterial(id, name, description, lectureId, resourceType);
    }


    @Override
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "lecture_id")
    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    @Column(name = "type")
    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        AdditionalMaterial.counter = counter;
    }

    @Override
    public String toString() {
        return "AddMaterials{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", lectureId=" + lectureId +
                ", resourceType=" + resourceType +
                '}';
    }
}

