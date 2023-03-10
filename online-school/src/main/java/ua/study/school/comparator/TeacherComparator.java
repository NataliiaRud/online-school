package ua.study.school.comparator;

import ua.study.school.models.Teacher;

import java.util.Comparator;

public class TeacherComparator implements Comparator<Teacher> {
    @Override
    public int compare(Teacher o1, Teacher o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
