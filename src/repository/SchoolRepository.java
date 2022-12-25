package repository;
import models.School;
public class SchoolRepository {
    private School[] schools = new School[0];
    private int lastIndex = -1;
    public void addSchool(School school) {
        School[] newSchools = new School[3 * schools.length / 2 + 1];
        for (int i = 0; i < schools.length; i++) {
            newSchools[i] = schools[i];
        }
        lastIndex++;
        newSchools[lastIndex] = school;
        this.schools = newSchools;
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
