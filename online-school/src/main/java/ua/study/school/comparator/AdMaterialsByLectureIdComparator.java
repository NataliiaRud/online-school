package ua.study.school.comparator;

import ua.study.school.models.AdditionalMaterial;

import java.util.Comparator;

public class AdMaterialsByLectureIdComparator implements Comparator<AdditionalMaterial> {
    @Override
    public int compare(AdditionalMaterial am1, AdditionalMaterial am2) {
        return Integer.compare(am1.getLectureId(), am2.getLectureId());
    }
}