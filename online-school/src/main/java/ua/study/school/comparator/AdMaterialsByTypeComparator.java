package ua.study.school.comparator;

import ua.study.school.models.AddMaterials;

import java.util.Comparator;

public class AdMaterialsByTypeComparator implements Comparator<AddMaterials> {
    @Override
    public int compare(AddMaterials am1, AddMaterials am2) {
        return am1.getResourceType().name().compareTo(am2.getResourceType().name());
    }
}

