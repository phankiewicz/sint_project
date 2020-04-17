import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum StudentDao {
    instance;

    private Map<Integer, Student> students = new HashMap<Integer, Student>();

    private StudentDao() {

        //pumping-in some default data
        Student student = new Student(1, "Piotr", "Hankiewicz", new Date());
        students.put(1, student);
        student = new Student(2, "Jan", "Kowalski", new Date());
        students.put(2, student);

    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

}