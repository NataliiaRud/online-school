package repository;
import models.Lecture;
import models.Students;
public class StudentsRepository {
    private Students[] studentsArray = new Students[0];
    private Students[] newStudentsArray = new Students[0];
    private int lastIndex = -1;
    public void addStudents(Students students) {
        lastIndex++;
        if (lastIndex >= studentsArray.length) {
        Students[] newStudentsArray = new Students[3 * studentsArray.length / 2 + 1];
            System.arraycopy(studentsArray, 0, newStudentsArray, 0, studentsArray.length);
//            this.studentsArray = newStudentsArray;
        }
        this.studentsArray[lastIndex] = students;
    }

    public Students getStudents(int studentsId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (studentsArray[i].getId() == studentsId) {
                return studentsArray[i];
            }
        }
        return null;
    }
    public Students[] getAllStudents() {
        return this.studentsArray;
    }
}
