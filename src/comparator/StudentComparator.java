package comparator;
import models.Students;
import java.util.Comparator;

public class StudentComparator implements Comparator<Students>{
    @Override
    public int compare(Students o1, Students o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}








