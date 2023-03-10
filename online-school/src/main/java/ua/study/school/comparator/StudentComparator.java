package ua.study.school.comparator;

import ua.study.school.models.Students;

import java.util.Comparator;

public class StudentComparator implements Comparator<Students> {
    @Override
    public int compare(Students o1, Students o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}








