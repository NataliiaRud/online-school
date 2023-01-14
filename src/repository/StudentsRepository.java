//package repository;
//import models.Students;
//
//public class StudentsRepository implements BaseRepository<Students> {
//    private Students[] studentsArray = new Students[0];
//
//    private int lastIndex = -1;
//
//
//
//
//    @Override
//    public void add(Students students) {
//        lastIndex++;
//        if (lastIndex >= studentsArray .length) {
//            Students[] newStudentsArray  = new Students[3 * studentsArray.length / 2 + 1];
//            System.arraycopy(studentsArray, 0, newStudentsArray, 0, studentsArray.length);
//            this.studentsArray = newStudentsArray;
//        }
//        this.studentsArray[lastIndex] = students;
//    }
//
//    @Override
//    public Students getById(int id) {
//        for (int i = 0; i <= lastIndex; i++) {
//            if (studentsArray[i].getId() == id) {
//                return studentsArray[i];
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Students[] getAll() {
//        return this.studentsArray;
//    }
//
//    @Override
//    public void deleteById(int id) {
//        for (int i = 0; i <= lastIndex; i++) {
//            if (studentsArray[i].getId() == id) {
//                studentsArray[i]=null;
//            }
//        }
//    }
//}