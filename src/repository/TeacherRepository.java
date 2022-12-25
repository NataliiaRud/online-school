package repository;
import models.Teacher;
public class TeacherRepository {
    private Teacher[] teachers = new Teacher[0];
    private int lastIndex = -1;
    public void addLecture(Teacher teacher) {
        Teacher[] newTeachers = new Teacher[3 * teachers.length / 2 + 1];
        for (int i = 0; i < teachers.length; i++) {
            newTeachers[i] = teachers[i];
        }
        lastIndex++;
        newTeachers[lastIndex] = teacher;
        this.teachers = newTeachers;
    }
    public Teacher getLecture(int teacherId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (teachers[i].getTeacherId() == teacherId) {
                return teachers[i];
            }
        }
        return null;
    }
    public Teacher[] getAllTeachers() {
        return this.teachers;
    }
}
