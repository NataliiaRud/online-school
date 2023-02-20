package comparator;
import models.AddMaterials;
import java.util.Comparator;

public class AdMaterialsByLectureIdComparator implements Comparator<AddMaterials> {
    @Override
    public int compare(AddMaterials am1, AddMaterials am2) {
        return Integer.compare(am1.getLectureId(), am2.getLectureId());
    }
}
