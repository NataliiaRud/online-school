//package repository;
//import models.School;
//
//public class SchoolRepository implements BaseRepository<School> {
//    private School[] schools = new School[0];
//
//    private int lastIndex = -1;
//
//
//
//
//    @Override
//    public void add(School school) {
//        lastIndex++;
//        if (lastIndex >= schools.length) {
//            School[] newSchools = new School[3 * schools.length / 2 + 1];
//            System.arraycopy(schools, 0, newSchools, 0, schools.length);
//            this.schools = newSchools;
//        }
//        this.schools[lastIndex] = school;
//    }
//
//    @Override
//    public School getById(int id) {
//        for (int i = 0; i <= lastIndex; i++) {
//            if (schools[i].getId() == id) {
//                return schools[i];
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public School[] getAll() {
//        return this.schools;
//    }
//
//    @Override
//    public void deleteById(int id) {
//        for (int i = 0; i <= lastIndex; i++) {
//            if (schools[i].getId() == id) {
//                schools[i]=null;
//            }
//        }
//    }
//}