import comparator.*;
import models.*;
import repository.*;
import service.LectureService;

import java.util.*;


import static models.AddMaterials.createAddMaterials;
import static service.AddMaterialsService.createAddMaterials1;

public class Main {

    public static final String CHOOSE_LECTURE_PARAMETERS = "%s: Course %s, Teacher: %s, Student: %s%n";

    public static void main(String[] args) {

        TeacherRepository teacherRepository = new TeacherRepository();
        Teacher teacher1 = new Teacher(1, "John", "Doe", Role.TEACHER, 1, "11111111111", "@1111");
        Teacher teacher2 = new Teacher(2, "Larry", "Paige", Role.TEACHER, 1, "22222222222", "@2222");
        Teacher teacher3 = new Teacher(3, "Brandon", "Walsh", Role.TEACHER, 1, "33333333333", "@3333");

        teacherRepository.add(teacher1);
        teacherRepository.add(teacher2);
        teacherRepository.add(teacher3);

        StudentsRepository studentsRepository = new StudentsRepository();
        Students student1 = new Students(1, "Alex", "Smith", Role.STUDENT,1, "44444444444", "@4444");
        Students student2 = new Students(2, "Xi", "Lee", Role.STUDENT, 1, "55555555555", "@5555");
        Students student3 = new Students(3, "Wishi", "Anan", Role.STUDENT, 1, "66666666666", "@6666");

        studentsRepository.add(student1);
        studentsRepository.add(student2);
        studentsRepository.add(student3);

        CourseRepository courseRepository = new CourseRepository();
        Course course1 = Course.createCourse(1, "Java Basic");
        Course course2 = Course.createCourse(2, "Java Advanced");
        Course course3 = Course.createCourse(3, "Java Pro");
        Course course4 = Course.createCourse(4, "Java 18");

        courseRepository.add(course1);
        courseRepository.add(course2);
        courseRepository.add(course3);
        courseRepository.add(course4);


        HomeAssignment homeAssignment1 = new HomeAssignment(1, "hw", 1, "task1");
        HomeAssignment homeAssignment2 = new HomeAssignment(2, "hw", 2, "task2");
        HomeAssignment homeAssignment3 = new HomeAssignment(3, "hw", 3, "task3");

        AddMaterialsRepository addMaterialsRepository = new AddMaterialsRepository();
        AddMaterials addMaterial1 = createAddMaterials1();
        AddMaterials addMaterial2 = createAddMaterials1();
        AddMaterials addMaterial3 = createAddMaterials1();

        addMaterialsRepository.add(addMaterial1);
        addMaterialsRepository.add(addMaterial2);
        addMaterialsRepository.add(addMaterial3);



        HomeAssignment[] homeAssignments1 = {homeAssignment1, homeAssignment2, homeAssignment3};
        AddMaterials[] addMaterials1 = {addMaterial1, addMaterial2, addMaterial3};



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
                    homeAssignments1,
                    addMaterials1);
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
                    homeAssignments1,
                    addMaterials1);

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
            System.out.println(lectureRepository.getSize());




        }


        List<Lecture> allLectures = lectureRepository.findAll();

        System.out.printf(allLectures.toString());

        sorting(teacherRepository, studentsRepository, courseRepository, addMaterialsRepository, scanner);
    }

    private static void sorting(TeacherRepository teacherRepository, StudentsRepository studentsRepository,
                                CourseRepository courseRepository, AddMaterialsRepository addMaterialsRepository,
                                Scanner scanner) {
        // Home assignment 18 (1)
        System.out.println("18-1 Sorting a copy of the list of courses by name");
        List<Course> copyOfCoursesList = new ArrayList<>(courseRepository.getAll());
        copyOfCoursesList.sort(new CourseComparator());
        System.out.println("Printing the sorted copy of the list of courses");
        System.out.println(copyOfCoursesList);
        System.out.println();

        // Home assignment 18 (2)
        System.out.println("18-2 Sorting a copy of the list of teachers by name");
        List<Teacher> copyOfTeachersList = new ArrayList<>(teacherRepository.getAll());
        copyOfTeachersList.sort(new TeacherComparator());
        System.out.println("Printing the sorted copy of the list of teachers");
        System.out.println(copyOfTeachersList);
        System.out.println();

        System.out.println("18-2 Sorting a copy of the list of students by name");
        List<Students> copyOfStudentsList = new ArrayList<>(studentsRepository.getAll());
        copyOfStudentsList.sort(new StudentComparator());
        System.out.println("Printing the sorted copy of the list of students");
        System.out.println(copyOfStudentsList);
        System.out.println();

        System.out.println("Please select additional materials sorting mode:");
        System.out.println("1 - by id (default), 2 - by lecture number, 3 - by type");
        List<AddMaterials> copyOfAdMaterials = new ArrayList<>(addMaterialsRepository.getAll());
        String selection = scanner.next();
        if ("2".equals(selection)) {
            copyOfAdMaterials.sort(new AdMaterialsByLectureIdComparator());
        } else if ("3".equals(selection)) {
            copyOfAdMaterials.sort(new AdMaterialsByTypeComparator());
        } else {
            copyOfAdMaterials.sort(new AdMaterialsByIdComparator());
        }
        System.out.println("Printing the sorted copy of the additional materials");
        System.out.println(copyOfAdMaterials);
        System.out.println();
    }

    private static Lecture createLecture(CreateLectureWrapper createLectureWrapper) {
        Lecture lecture = null;
        try {
            switch (createLectureWrapper.categoryNumber()) {
                case 1:
                    lecture = Lecture.createLecture(1, "Java Chapter1", createLectureWrapper.teacher1(), createLectureWrapper.student1(),
                            createLectureWrapper.course1().getId(), createLectureWrapper.teacher1().getId(), "first lecture description",
                            createLectureWrapper.homeAssignments1, createLectureWrapper.addMaterials1);
                    break;
                case 2:
                    lecture = Lecture.createLecture(2, "Java Chapter2", createLectureWrapper.teacher2(), createLectureWrapper.student2(),
                            createLectureWrapper.course2().getId(), createLectureWrapper.teacher2().getId(), "second lecture description",
                            createLectureWrapper.homeAssignments1, createLectureWrapper.addMaterials1);
                    break;
                case 3:
                    lecture = Lecture.createLecture(3, "Java Chapter3", createLectureWrapper.teacher3(), createLectureWrapper.student3(),
                            createLectureWrapper.course3().getId(), createLectureWrapper.teacher3().getId(), "third lecture description",
                            createLectureWrapper.homeAssignments1, createLectureWrapper.addMaterials1);
                    break;
                default:
                    throw new IllegalArgumentException("test exception");
            }
            return lecture;
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private record CreateLectureWrapper(Teacher teacher1, Teacher teacher2, Teacher teacher3, Students student1,
                                        Students student2, Students student3, Course course1, Course course2, Course course3,
                                        int categoryNumber, HomeAssignment[] homeAssignments1,
                                        AddMaterials[] addMaterials1) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CreateLectureWrapper wrapper = (CreateLectureWrapper) o;
            return categoryNumber == wrapper.categoryNumber && Objects.equals(teacher1, wrapper.teacher1) && Objects.equals(teacher2, wrapper.teacher2) && Objects.equals(teacher3, wrapper.teacher3) && Objects.equals(student1, wrapper.student1) && Objects.equals(student2, wrapper.student2) && Objects.equals(student3, wrapper.student3) && Objects.equals(course1, wrapper.course1) && Objects.equals(course2, wrapper.course2) && Objects.equals(course3, wrapper.course3);
        }

        @Override
        public int hashCode() {
            return Objects.hash(teacher1, teacher2, teacher3, student1, student2, student3, course1, course2, course3, categoryNumber);
        }
    }
}







