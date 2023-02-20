package models;

public class AddMaterials extends Base {

    private String name;
    private Integer lectureId;
    private ResourceType resourceType;
    private static int counter;
    public AddMaterials(Integer id, String name, Integer lectureId, ResourceType resourceType) {
        super(id);
        this.name = name;
        this.lectureId = lectureId;
        this.resourceType = resourceType;
        counter++;
    }


    public static AddMaterials createAddMaterials(Integer id, String name, Integer lectureId, ResourceType resourceType) {
        return new AddMaterials(id, name, lectureId, resourceType);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

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
        AddMaterials.counter = counter;
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

