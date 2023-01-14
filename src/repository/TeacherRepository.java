//package repository;
//import models.Teacher;
//
//public class TeacherRepository implements BaseRepository<Teacher> {
//    private Teacher[] teachers = new Teacher[0];
//
//    private int lastIndex = -1;
//
//
//
//
//    @Override
//    public void add(Teacher teacher) {
//        lastIndex++;
//        if (lastIndex >= teachers.length) {
//            Teacher[] newTeachers = new Teacher[3 * teachers.length / 2 + 1];
//            System.arraycopy(teachers, 0, newTeachers, 0, teachers.length);
//            this.teachers = newTeachers;
//        }
//        this.teachers[lastIndex] = teacher;
//    }
//
//    @Override
//    public Teacher getById(int id) {
//        for (int i = 0; i <= lastIndex; i++) {
//            if (teachers[i].getId() == id) {
//                return teachers[i];
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Teacher[] getAll() {
//        return this.teachers;
//    }
//
//    @Override
//    public void deleteById(int id) {
//        for (int i = 0; i <= lastIndex; i++) {
//            if (teachers[i].getId() == id) {
//                teachers[i]=null;
//            }
//        }
//    }
//}