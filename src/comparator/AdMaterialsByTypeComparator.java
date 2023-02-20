package comparator;
import models.AddMaterials;
import java.util.Comparator;

public class AdMaterialsByTypeComparator implements Comparator<AddMaterials> {
    public int compare(AddMaterials am1, AddMaterials am2) {
        return am1.getResourceType().name().compareTo(am2.getResourceType().name());
    }
}

