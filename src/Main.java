import models.*;
import repository.CourseRepository;
import repository.LectureRepository;
import service.LectureService;


import java.util.Scanner;


public class Main {

    public static final String CHOOSE_LECTURE_PARAMETERS = "%s: Course %s, Teacher: %s, Student: %s%n";

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

        CourseRepository courseRepository = new CourseRepository();
        Course course4 = Course.createCourse(4, "Java 18");
        courseRepository.addCourse(course4);

        LectureRepository lectureRepository = new LectureRepository();

        // creating 3 lectures automatically
        int i = 0;
        for (int counter = 1; counter <= 3; counter++) {
            CreateLectureWrapper wrapper = new CreateLectureWrapper(teacher1,
                    teacher2,
                    teacher3,
                    student1,
                    student2,
                    student3,
                    course1,
                    course2,
                    course3,
                    counter); // using counter (1-3) as category number

            Lecture firstLecture = createLecture(wrapper);
            lectureRepository.addLecture(firstLecture);

            i++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("You have created " + Lecture.getCounter() + " lectures total");
        System.out.println("Would you like to create another lecture? yes/no");

        while ("yes".equalsIgnoreCase(scanner.next()) && i<8) {
            System.out.println("To create a Lecture object, choose a course category, type the proper number:");
            System.out.printf(CHOOSE_LECTURE_PARAMETERS, 1, course1.getId(), teacher1.getLastName(), student1.getLastName());
            System.out.printf(CHOOSE_LECTURE_PARAMETERS, 2, course2.getId(), teacher2.getLastName(), student2.getLastName());
            System.out.printf(CHOOSE_LECTURE_PARAMETERS, 3, course3.getId(), teacher3.getLastName(), student3.getLastName());

            int categoryNumber = scanner.nextInt();

            CreateLectureWrapper wrapper = new CreateLectureWrapper(teacher1,
                    teacher2,
                    teacher3,
                    student1,
                    student2,
                    student3,
                    course1,
                    course2,
                    course3,
                    categoryNumber);

            Lecture firstLecture = createLecture(wrapper);
            lectureRepository.addLecture(firstLecture);

            System.out.println(firstLecture != null ? "You've created lecture " + firstLecture.toString() + " of course " + firstLecture.getCourseId() : "Lecture is null");
            System.out.println("You have created " + Lecture.getCounter() + " lectures total");
            System.out.println("Would you like to create another lecture? yes/no");
            i++;
        }

        System.out.println("Would you like to print lecture ids? yes/no");
        if ("yes".equalsIgnoreCase(scanner.next())) {
            LectureService lectureService = new LectureService(lectureRepository);
            lectureService.printLectureIds();

        }
    }
    private static Lecture createLecture(CreateLectureWrapper createLectureWrapper) {
        Lecture lecture = null;
        switch (createLectureWrapper.categoryNumber()) {
            case 1:
                lecture = Lecture.createLecture(1, "Java Chapter1", createLectureWrapper.teacher1(), createLectureWrapper.student1(), createLectureWrapper.course1().getId());
                break;
            case 2:
                lecture = Lecture.createLecture(2, "Java Chapter2", createLectureWrapper.teacher2(), createLectureWrapper.student2(), createLectureWrapper.course2().getId());
                break;
            case 3:
                lecture = Lecture.createLecture(3, "Java Chapter3", createLectureWrapper.teacher3(), createLectureWrapper.student3(), createLectureWrapper.course3().getId());
                break;             default:                 System.out.println("No such category exist");
        }
        return lecture;
    }

    private record CreateLectureWrapper(Teacher teacher1, Teacher teacher2, Teacher teacher3, Students student1,
                                        Students student2, Students student3, Course course1, Course course2, Course course3,
                                        int categoryNumber) {
    }
}







