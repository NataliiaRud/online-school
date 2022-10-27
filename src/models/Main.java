package models;

public class Main {
    public static void main(String[] args) {
        Teacher teacher1= new Teacher(1, "John", "Doe");
        Students student1= new Students(1, "Alex", "Smith");
        HomeAssignment homeAssignment1= new HomeAssignment(1, "Develope a programm 1");
        HomeAssignment homeAssignment2= new HomeAssignment(2, "Develope a programm 2");
        HomeAssignment homeAssignment3= new HomeAssignment(3, "Develope a programm 3");
        AddMaterials addMaterial1= new AddMaterials(1, "Book 1");
        AddMaterials addMaterial2= new AddMaterials(2, "Book 2");
        AddMaterials addMaterial3= new AddMaterials(3, "Book 3");
        Lecture lecture1 = new Lecture (1, "Java Chapter1", teacher1, student1, homeAssignment1, addMaterial1);
        Lecture lecture2 = new Lecture (2, "Java Chapter2", teacher1, student1, homeAssignment2, addMaterial2);
        Lecture lecture3 = new Lecture (3, "Java Chapter3", teacher1, student1, homeAssignment3, addMaterial3);

        System.out.println(Lecture.lectureCounter);
    }
}
