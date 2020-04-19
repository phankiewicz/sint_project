import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public enum StudentDao {
    instance;

    private AtomicInteger counter = new AtomicInteger(2);
    private Map<Integer, Student> students = new ConcurrentHashMap<>();

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

    public synchronized Student createStudent(Student student) {
        Integer current_counter = counter.incrementAndGet();
        student.setIndex(current_counter);
        students.put(current_counter, student);
        return students.get(current_counter);
    }

}