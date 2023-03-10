package ua.study.school.comparator;

import ua.study.school.models.Course;

import java.util.Comparator;


public class CourseComparator implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
