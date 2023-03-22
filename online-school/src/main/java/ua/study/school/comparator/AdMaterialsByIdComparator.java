package ua.study.school.comparator;

import ua.study.school.models.AdditionalMaterial;

import java.util.Comparator;

public class AdMaterialsByIdComparator implements Comparator<AdditionalMaterial> {
    @Override
    public int compare(AdditionalMaterial am1, AdditionalMaterial am2) {
        return Integer.compare(am1.getId(), am2.getId());
    }
}


