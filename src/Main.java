import models.*;
import repository.CourseRepository;
import repository.LectureRepository;
import repository.StudentsRepository;
import service.LectureService;
import repository.HomeAssignmentRepository;

import java.util.Scanner;


public class Main {

    public static final String CHOOSE_LECTURE_PARAMETERS = "%s: Course %s, Teacher: %s, Student: %s%n";

    public static void main(String[] args) {

        Teacher teacher1 = new Teacher(1, "John", "Doe", Role.TEACHER, 1, "11111111111", "@1111");
        Teacher teacher2 = new Teacher(2, "Larry", "Paige", Role.TEACHER, 1, "22222222222", "@2222");
        Teacher teacher3 = new Teacher(3, "Brandon", "Walsh", Role.TEACHER, 1, "33333333333", "@3333");
        Students student1 = new Students(1, "Alex", "Smith", Role.STUDENT,1, "44444444444", "@4444");
        Students student2 = new Students(2, "Xi", "Lee", Role.STUDENT, 1, "55555555555", "@5555");
        Students student3 = new Students(3, "Wishi", "Anan", Role.STUDENT, 1, "66666666666", "@6666");

        Course course1 = new Course(1, "Java Basic");
        Course course2 = new Course(2, "Java Advanced");
        Course course3 = new Course(3, "Java Pro");

        HomeAssignment homeAssignment1 = new HomeAssignment(1, "hw", 1, "task1");
        HomeAssignment homeAssignment2 = new HomeAssignment(2, "hw", 2, "task2");
        HomeAssignment homeAssignment3 = new HomeAssignment(3, "hw", 3, "task3");

        HomeAssignment[] homeAssignments1 = {homeAssignment1, homeAssignment2, homeAssignment3};


        CourseRepository courseRepository = new CourseRepository();
        Course course4 = Course.createCourse(4, "Java 18");
        courseRepository.add(course4);

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
                    counter,
                    homeAssignments1);
            // using counter (1-3) as category number

            Lecture firstLecture = createLecture(wrapper);
            lectureRepository.add(firstLecture);

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
                    categoryNumber,
                    homeAssignments1);

            Lecture firstLecture = createLecture(wrapper);
            lectureRepository.add(firstLecture);

            System.out.println(firstLecture != null ? "You've created lecture " + firstLecture.toString() + " of course " + firstLecture.getCourseId() : "Lecture is null");
            System.out.println("You have created " + Lecture.getCounter() + " lectures total");
            System.out.println("Would you like to create another lecture? yes/no");
            i++;
        }

        System.out.println("Would you like to print lecture ids? yes/no");
        if ("yes".equalsIgnoreCase(scanner.next())) {
            LectureService lectureService = new LectureService(lectureRepository);
            lectureService.printLectureIds();
            Person teacher6 = Person.createPerson(1, "aaa", "vvv", Role.TEACHER, 1, "55555555555", "@rrr");
            System.out.println(teacher6.getFirstName());
            System.out.println(lectureRepository.getLecturesSize());

        }
    }

    private static Lecture createLecture(CreateLectureWrapper createLectureWrapper) {
        Lecture lecture = null;
        switch (createLectureWrapper.categoryNumber()) {
            case 1:
                lecture = Lecture.createLecture(1, "Java Chapter1", createLectureWrapper.teacher1(), createLectureWrapper.student1(),
                        createLectureWrapper.course1().getId(), createLectureWrapper.teacher1().getId(), "first lecture description",
                        createLectureWrapper.homeAssignments1);
                break;
            case 2:
                lecture = Lecture.createLecture(2, "Java Chapter2", createLectureWrapper.teacher2(), createLectureWrapper.student2(),
                        createLectureWrapper.course2().getId(), createLectureWrapper.teacher2().getId(), "second lecture description",
                        createLectureWrapper.homeAssignments1);
                break;
            case 3:
                lecture = Lecture.createLecture(3, "Java Chapter3", createLectureWrapper.teacher3(), createLectureWrapper.student3(),
                        createLectureWrapper.course3().getId(), createLectureWrapper.teacher3().getId(), "third lecture description",
                        createLectureWrapper.homeAssignments1);
                break;             default:                 System.out.println("No such category exist");
        }
        return lecture;
    }

    private record CreateLectureWrapper(Teacher teacher1, Teacher teacher2, Teacher teacher3, Students student1,
                                        Students student2, Students student3, Course course1, Course course2, Course course3,
                                        int categoryNumber, HomeAssignment[] homeAssignments1) {
    }
}







