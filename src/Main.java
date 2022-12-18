import models.*;

import java.sql.SQLOutput;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Teacher teacher1 = new Teacher(1, "John", "Doe");
        Teacher teacher2 = new Teacher(2, "Larry", "Paige");
        Teacher teacher3 = new Teacher(3, "Brandon", "Walsh");
        Students student1 = new Students(1, "Alex", "Smith");
        Students student2 = new Students(2, "Xi", "Lee");
        Students student3 = new Students(3, "Wishi", "Anan");
        Course course1 = new Course(1, "Java Basic");
        Course course2 = new Course(2, "Java Advanced");
        Course course3 = new Course(3, "Java Pro");
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        do {
            System.out.println("To create a Lecture object, choose a course category, type the proper number:");
            System.out.println("1: Course " + course1.id + ", Teacher: " + teacher1.getName() + ", Student: " + student1.getName());
            System.out.println("2: Course " + course2.id + ", Teacher: " + teacher2.getName() + ", Student: " + student2.getName());
            System.out.println("3: Course " + course3.id + ", Teacher: " + teacher3.getName() + ", Student: " + student3.getName());

            int categoryNumber = scanner.nextInt();
            Lecture firstLecture = createLecture(teacher1, teacher2, teacher3, student1, student2, student3, course1, course2, course3, categoryNumber);
            System.out.println(firstLecture != null ? "You've created lecture " + firstLecture.toString() + " of course " + firstLecture.getCourseId() : "Lecture is null");
            System.out.println("You have created " + Lecture.lectureCounter + " lectures total");
            System.out.println("Would you like to create another lecture? y/n");
            i++;
        }
        while ("y".equalsIgnoreCase(scanner.next()) && i<8);

    }
    private static Lecture createLecture(Teacher teacher1, Teacher teacher2, Teacher teacher3,
                                         Students student1, Students student2, Students student3,
                                         Course course1, Course course2, Course course3, int categoryNumber) {
        Lecture lecture = null;         switch (categoryNumber) {
            case 1:
                lecture = Lecture.createLecture(1, "Java Chapter1", teacher1, student1, course1.getId());
                break;
            case 2:
                lecture = Lecture.createLecture(2, "Java Chapter2", teacher2, student2, course2.getId());
                break;
            case 3:
                lecture = Lecture.createLecture(3, "Java Chapter3", teacher3, student3, course3.getId());
                break;             default:                 System.out.println("No such category exist");
        }
        return lecture;
    }
}







