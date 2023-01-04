package repository;
import models.Lecture;
import models.School;
public class SchoolRepository {
    private School[] schools = new School[0];
    private School[] newSchools = new School[0];
    private int lastIndex = -1;
    public void addSchool(School school) {
        lastIndex++;
        if (lastIndex >= schools.length) {
        School[] newSchools = new School[3 * schools.length / 2 + 1];
            System.arraycopy(schools, 0, newSchools, 0, schools.length);
            this.schools = newSchools;
        }
        this.schools[lastIndex] = school;
    }

    public School getSchool(int schoolId) {
        for (int i = 0; i <= lastIndex; i++) {
            if (schools[i].getSchoolId() == schoolId) {
                return schools[i];
            }
        }
        return null;
    }
    public School[] getAllSchools() {
        return this.schools;
    }
}
