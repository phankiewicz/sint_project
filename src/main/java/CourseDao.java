import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public enum CourseDao {
    instance;

    private AtomicInteger counter = new AtomicInteger(2);
    private Map<Integer, Course> courses = new ConcurrentHashMap<>();

    private CourseDao() {

        //pumping-in some default data
        Course course = new Course(1, "course1", "teacher1");
        courses.put(1, course);
        course = new Course(2, "course2", "teacher2");
        courses.put(2, course);

    }

    public Map<Integer, Course> get() {
        return courses;
    }

    public synchronized Course create(Course course) {
        Integer current_counter = counter.incrementAndGet();
        course.setId(current_counter);
        courses.put(current_counter, course);
        return courses.get(current_counter);
    }

}