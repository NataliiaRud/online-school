package ua.study.school.comparator;

import ua.study.school.models.AdditionalMaterial;

import java.util.Comparator;

public class AdMaterialsByTypeComparator implements Comparator<AdditionalMaterial> {
    @Override
    public int compare(AdditionalMaterial am1, AdditionalMaterial am2) {
        return am1.getResourceType().name().compareTo(am2.getResourceType().name());
    }
}

