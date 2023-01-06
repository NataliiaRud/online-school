package repository;
import models.Lecture;
import models.Teacher;
public class TeacherRepository {
    private Teacher[] teachers = new Teacher[0];
    private Teacher[] newTeachers = new Teacher[0];
    private int lastIndex = -1;
    public void addLecture(Teacher teacher) {
        lastIndex++;
        if (lastIndex >= teachers.length) {
        Teacher[] newTeachers = new Teacher[3 * teachers.length / 2 + 1];
            System.arraycopy(teachers, 0, newTeachers, 0, teachers.length);
//            this.teachers = newTeachers;
        }
        this.teachers[lastIndex] = teacher;
    }

    public Teacher getLecture(int teacherId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (teachers[i].getId() == teacherId) {
                return teachers[i];
            }
        }
        return null;
    }
    public Teacher[] getAllTeachers() {
        return this.teachers;
    }
}
