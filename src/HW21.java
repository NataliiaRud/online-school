import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class HW21 {
    private static class Student {
        private String name;
        private String task;

        private int time;

        public Student(String name, String task) {
            this.name = name;
            this.task = task;
        }
    }

    public static void execute() throws InterruptedException {
        int N = 10;

        List<String> tasks = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            tasks.add("Task " + i);
        }

        Collections.shuffle(tasks);

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Student student = new Student("Student " + (i + 1), tasks.get(i));
            students.add(student);
            System.out.println(students.get(i).name + " got " + tasks.get(i));
        }

        long start = System.currentTimeMillis();
        List<Student> completion = new CopyOnWriteArrayList<>();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Student student = students.get(i);
            Thread thread = new Thread(() -> {
                int secondsToSleep = new Random().nextInt(6 + 1) + 8;
                try {
                    Thread.sleep(secondsToSleep * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                student.time = (int) ((System.currentTimeMillis() - start) / 1000);
                completion.add(student);
            });

            threads.add(thread);
            thread.start();
        }

        System.out.println();
        System.out.println("Determining results ...");

        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < completion.size(); i++) {
            Student student = completion.get(i);
            System.out.println("Place " + (i + 1) + ": "
                    + student.name
                    + ", " + student.task
                    + ", " + student.time + " seconds");
        }

        System.out.println();
        System.out.println("The students below did not complete their tasks on time: ");
        for (Student student : completion) {
            if (student.time <= 12) {
                continue;
            }

            System.out.println(
                    student.name
                            + ", " + student.task
                            + ", " + student.time + " seconds");
        }
    }
}
