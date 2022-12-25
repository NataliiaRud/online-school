package repository;
import models.Students;
public class StudentsRepository {
    private Students[] studentsArray = new Students[0];
    private int lastIndex = -1;
    public void addStudents(Students students) {
        Students[] newStudentsArray = new Students[3 * studentsArray.length / 2 + 1];
        for (int i = 0; i < studentsArray.length; i++) {
            newStudentsArray[i] = studentsArray[i];
        }
        lastIndex++;
        newStudentsArray[lastIndex] = students;
        this.studentsArray = newStudentsArray;
    }
    public Students getStudents(int studentsId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (studentsArray[i].getStudentsId() == studentsId) {
                return studentsArray[i];
            }
        }
        return null;
    }
    public Students[] getAllStudents() {
        return this.studentsArray;
    }
}
