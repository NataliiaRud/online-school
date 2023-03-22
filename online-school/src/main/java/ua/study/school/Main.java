package ua.study.school;

import ua.study.school.comparator.*;
import ua.study.school.models.*;
import ua.study.school.repository.*;
import ua.study.school.service.LectureService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static ua.study.school.service.AddMaterialsService.createAddMaterials1;

public class Main {
    public static final String CHOOSE_LECTURE_PARAMETERS = "%s: Course %s, Teacher: %s, Student: %s%n";

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        TeacherRepository teacherRepository = new TeacherRepository();
        Teacher teacher1 = new Teacher(1, "John", "Doe", 1, "11111111111", "@1111");
        Teacher teacher2 = new Teacher(2, "Larry", "Paige", 1, "22222222222", "@2222");
        Teacher teacher3 = new Teacher(3, "Brandon", "Walsh", 1, "33333333333", "@3333");
        teacherRepository.add(teacher1);
        teacherRepository.add(teacher2);
        teacherRepository.add(teacher3);

        StudentsRepository studentsRepository = new StudentsRepository();
        Student student1 = new Student(1, "Alex", "Smith", 1, "44444444444", "@4444");
        Student student2 = new Student(2, "Xi", "Lee", 1, "55555555555", "@5555");
        Student student3 = new Student(3, "Wishi", "Anan", 1, "66666666666", "@6666");
        studentsRepository.add(student1);
        studentsRepository.add(student2);
        studentsRepository.add(student3);

        CourseRepository courseRepository = new CourseRepository();
        System.out.println("Number of courses in the database: " + courseRepository.getSize());
        Course course1 = courseRepository.getById(1);
        Course course2 = courseRepository.getById(2);
        Course course3 = courseRepository.getById(3);

        HomeAssignmentRepository homeAssignmentRepository = new HomeAssignmentRepository();
        HomeAssignment homeAssignment1 = new HomeAssignment(1, "hw", 1, "task1");
        HomeAssignment homeAssignment2 = new HomeAssignment(2, "hw", 2, "task2");
        HomeAssignment homeAssignment3 = new HomeAssignment(3, "hw", 3, "task3");
        homeAssignmentRepository.add(homeAssignment1);
        homeAssignmentRepository.add(homeAssignment2);
        homeAssignmentRepository.add(homeAssignment3);

        AddMaterialsRepository addMaterialsRepository = new AddMaterialsRepository();
        AdditionalMaterial addMaterial1 = createAddMaterials1();
        AdditionalMaterial addMaterial2 = createAddMaterials1();
        AdditionalMaterial addMaterial3 = createAddMaterials1();
        addMaterialsRepository.add(addMaterial1);
        addMaterialsRepository.add(addMaterial2);
        addMaterialsRepository.add(addMaterial3);


        HomeAssignment[] homeAssignments1 = {homeAssignment1, homeAssignment2, homeAssignment3};
        AdditionalMaterial[] additionalMaterial1 = {addMaterial1, addMaterial2, addMaterial3};

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
                    additionalMaterial1);
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
                    additionalMaterial1);

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
            Optional<Person> teacher6 = Optional.ofNullable(Person.createPerson(1, "aaa", "vvv", Role.TEACHER, 1, "55555555555", "@rrr"));
            System.out.println(teacher6.isPresent() ? teacher6.get().getFirstName() : "teacher6 is null");
            System.out.println(lectureRepository.getSize());
        }


        List<Lecture> allLectures = lectureRepository.findAll();
        System.out.printf(allLectures.toString());

        // Home assignment 18
        sorting(teacherRepository, studentsRepository, courseRepository, addMaterialsRepository, scanner);

        // Home assignment 19
        viewByLectureId(homeAssignmentRepository, addMaterialsRepository, scanner);

        // Home assignment 20
        HW20.execute();

        // Home assignment 21
        HW21.execute();

        // Home assignment 22
        HW22.execute();

        // Home assignment 25
        // serialization
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        for (Lecture lecture : lectureRepository.getAll()) {
            objectOutputStream.writeObject(lecture);
        }

        for (HomeAssignment homeAssignment : homeAssignmentRepository.getAll()) {
            objectOutputStream.writeObject(homeAssignment);
        }

        for (AdditionalMaterial additionalMaterial : addMaterialsRepository.getAll()) {
            objectOutputStream.writeObject(additionalMaterial);
        }

        for (Teacher teacher : teacherRepository.getAll()) {
            objectOutputStream.writeObject(teacher);
        }

        for (Student student : studentsRepository.getAll()) {
            objectOutputStream.writeObject(student);
        }

        byte[] serialized = byteArrayOutputStream.toByteArray();
        objectOutputStream.close();

        // deserialization
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serialized);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        Object object;
        try {
            while ((object = objectInputStream.readObject()) != null) {
                System.out.println(object);
            }
        } catch (EOFException e) {
            // ignored
        }

        objectInputStream.close();


        // Home assignment 27
        List<AdditionalMaterial> allAddMaterials = addMaterialsRepository.getAll();
        Map<Integer, List<AdditionalMaterial>> addMaterialsByLecture;
        addMaterialsByLecture = allAddMaterials
                .stream().collect(Collectors.groupingBy(
                        AdditionalMaterial::getLectureId,
                        Collectors.mapping((AdditionalMaterial a) -> a, Collectors.toList())));
        System.out.println(addMaterialsByLecture);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = calendar.getTime();

        // after given date
        System.out.println(lectureRepository.getAll().stream().filter(
                lecture -> {
                    Optional<Date> date = Optional.ofNullable(lecture.getLectureDate());
                    return date.isPresent() && date.get().after(startDate);
                }
        ).collect(Collectors.toList()));

        // before given date
        System.out.println(lectureRepository.getAll().stream().filter(
                lecture -> {
                    Optional<Date> date = Optional.ofNullable(lecture.getLectureDate());
                    return date.isPresent() && date.get().before(endDate);
                }
        ).collect(Collectors.toList()));

        // between given dates
        System.out.println(lectureRepository.getAll().stream().filter(
                lecture -> {
                    Optional<Date> date = Optional.ofNullable(lecture.getLectureDate());
                    return date.isPresent()
                            && date.get().after(startDate) && date.get().before(endDate);}
        ).collect(Collectors.toList()));

        // Home assignment 28, task 2
        System.out.println(teacherRepository.getAll().stream().filter(teacher -> {
            Optional<String> lastName = Optional.ofNullable(teacher.getLastName());

            return lastName.isPresent()
                    && lastName.get().length() > 0
                    && Character.isAlphabetic(lastName.get().charAt(0))
                    && lastName.get().toUpperCase().charAt(0) < 'N';
        }).collect(Collectors.toList()));

        // Home assignment 29, task 1
        Optional<Person> personCreationTest1 = Optional.ofNullable(Person.createPerson(2, "Jack", "Smith",
                Role.STUDENT, 1, "38067123456", "jack.smith@email.com"));
        System.out.println("Person added: " + personCreationTest1.isPresent());
        Optional<Person> personCreationTest2 = Optional.ofNullable(Person.createPerson(3, "Jack", "Smith",
                Role.STUDENT, 1, "38067123456", "jack.smith@email.com"));
        System.out.println("Person added: " + personCreationTest2.isPresent());

        // Home assignment 29, task 2
        List<Lecture> copyOfLectures = new ArrayList<>(lectureRepository.getAll());
        if (!copyOfLectures.isEmpty()) {
            copyOfLectures.sort((lecture1, lecture2) -> {
                if (lecture1.getLectureDate().compareTo(lecture2.getLectureDate()) == 0) {
                    return Integer.compare(lecture2.getHomeAssignments().length, lecture1.getHomeAssignments().length);
                }

                return lecture1.getLectureDate().compareTo(lecture2.getLectureDate());
            });
            System.out.println(
                    "The lecture that was created the earliest and has the most addition materials is: "
                            + copyOfLectures.get(0)
            );
        }


        // Home assignment 31, task 1
        Map<Integer, List<Lecture>> lecturesGroupedByTeacher =
                copyOfLectures.stream().collect(
                        Collectors.groupingBy(
                                lecture -> lecture.getId()
                        )
                );
        System.out.println("Home assignment 31, task 1: grouping lectures by teacher");
        System.out.println(lecturesGroupedByTeacher);

        // Home assignment 31, task 2
        Map<Integer, List<AdditionalMaterial>> additionalMaterialsByLecture =
                addMaterialsRepository.getAll().stream().collect(
                        Collectors.groupingBy(
                                AdditionalMaterial::getLectureId
                        )
                );
        System.out.println("Home assignment 31, task 2: grouping additional materials by lecture");
        System.out.println(additionalMaterialsByLecture);

        // Home assignment 31, task 3
        Map<String, String> emailTonameMap = teacherRepository.getAll().stream().collect(
                Collectors.toMap(Person::getEmail, teacher -> teacher.getFirstName() + " " + teacher.getLastName())
        );
        System.out.println("Home assignment 31, task 3: email -> first + last name");
        System.out.println(emailTonameMap);

        // Home assignment 31, task 4
        Files.write(Paths.get("students.txt"),
                studentsRepository.getAll().stream().map(Person::getEmail).collect(Collectors.toList())
        );
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
        List<Student> copyOfStudentList = new ArrayList<>(studentsRepository.getAll());
        copyOfStudentList.sort(new StudentComparator());
        System.out.println("Printing the sorted copy of the list of students");
        System.out.println(copyOfStudentList);
        System.out.println();

        System.out.println("Please select additional materials sorting mode:");
        System.out.println("1 - by id (default), 2 - by lecture number, 3 - by type");
        List<AdditionalMaterial> copyOfAdMaterials = new ArrayList<>(addMaterialsRepository.getAll());
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

    private static void viewByLectureId(HomeAssignmentRepository homeAssignmentRepository,
                                        AddMaterialsRepository addMaterialsRepository,
                                        Scanner scanner) {
        System.out.println("Enter lecture id: ");
        int lectureId = scanner.nextInt();

        System.out.println("Home assignments belonging to lecture " + lectureId);
        System.out.println(homeAssignmentRepository.getByLectureId(lectureId));
        System.out.println();
        System.out.println("Would you like to add new assignment to lecture " + lectureId + "? yes/no");
        if ("yes".equalsIgnoreCase(scanner.next())) {
            System.out.println("Enter new home assignment id: ");
            int id = scanner.nextInt();
            System.out.println("Enter new assignment description: ");
            String assignment = scanner.next();
            System.out.println("Enter new assignment task: ");
            String task = scanner.next();

            HomeAssignment newAssignment = new HomeAssignment(id, assignment, lectureId, task);
            homeAssignmentRepository.add(newAssignment);

            System.out.println("List of assignments for lecture " + lectureId + " has been updated:");
            System.out.println(homeAssignmentRepository.getByLectureId(lectureId));
        }

        System.out.println("Would you like to delete an assignment? yes/no");
        if ("yes".equalsIgnoreCase(scanner.next())) {
            System.out.println("Enter home assignment id to delete: ");
            int id = scanner.nextInt();
            homeAssignmentRepository.deleteById(id);

            System.out.println("List of assignments for lecture " + lectureId + " has been updated:");
            System.out.println(homeAssignmentRepository.getByLectureId(lectureId));
        }

        System.out.println("Additional materials belonging to lecture " + lectureId);
        System.out.println(addMaterialsRepository.getByLectureId(lectureId));
        System.out.println();
        System.out.println("Would you like to add additional materials to lecture " + lectureId + "? yes/no");
        if ("yes".equalsIgnoreCase(scanner.next())) {
            System.out.println("Enter new additional materials id: ");
            int id = scanner.nextInt();
            System.out.println("Enter new additional assignment name: ");
            String name = scanner.next();
            System.out.println("Enter new additional assignment description: ");
            String description = scanner.next();
            System.out.println("Enter new additional assignment type (default - URL): ");
            String type = scanner.next();
            ResourceType resourceType = ResourceType.URL;
            if ("book".equalsIgnoreCase(type)) {
                resourceType = ResourceType.BOOK;
            } else if ("video".equalsIgnoreCase(type)) {
                resourceType = ResourceType.VIDEO;
            }

            AdditionalMaterial additionalMaterial = new AdditionalMaterial(id, name, description, lectureId, resourceType);
            addMaterialsRepository.add(additionalMaterial);

            System.out.println("Additional materials belonging to lecture " + lectureId + " has been updated: ");
            System.out.println(addMaterialsRepository.getByLectureId(lectureId));
        }

        System.out.println("Would you like to delete additional material? yes/no");
        if ("yes".equalsIgnoreCase(scanner.next())) {
            System.out.println("Enter additional material id to delete: ");
            int id = scanner.nextInt();
            addMaterialsRepository.deleteById(id);

            System.out.println("Additional materials belonging to lecture " + lectureId + " has been updated: ");
            System.out.println(addMaterialsRepository.getByLectureId(lectureId));
        }
    }

    private static Lecture createLecture(CreateLectureWrapper createLectureWrapper) {
        Lecture lecture = null;
        try {
            switch (createLectureWrapper.categoryNumber()) {
                case 1:
                    lecture = Lecture.createLecture(1, "Java Chapter1", "first lecture description", createLectureWrapper.teacher1().getId(),
                            createLectureWrapper.course1().getId(),
                            createLectureWrapper.homeAssignments1, createLectureWrapper.additionalMaterial1);
                    break;
                case 2:
                    lecture = Lecture.createLecture(2, "Java Chapter2", "second lecture description", createLectureWrapper.teacher2().getId(),
                            createLectureWrapper.course2().getId(),
                            createLectureWrapper.homeAssignments1, createLectureWrapper.additionalMaterial1);
                    break;
                case 3:
                    lecture = Lecture.createLecture(3, "Java Chapter3", "third lecture description", createLectureWrapper.teacher3().getId(),
                            createLectureWrapper.course3().getId(),
                            createLectureWrapper.homeAssignments1, createLectureWrapper.additionalMaterial1);
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

    private record CreateLectureWrapper(Teacher teacher1, Teacher teacher2, Teacher teacher3, Student student1,
                                        Student student2, Student student3, Course course1, Course course2, Course course3,
                                        int categoryNumber, HomeAssignment[] homeAssignments1,
                                        AdditionalMaterial[] additionalMaterial1) {

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








