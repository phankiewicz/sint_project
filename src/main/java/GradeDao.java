import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public enum GradeDao {
    instance;

    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Grade> grades = new ConcurrentHashMap<>();

    private GradeDao() {
//        Course course = new Course(2, "course2", "teacher2");
//        Grade grade1 = new Grade(1, 4.5, new Date(), course, 1);
//        Grade grade2 = new Grade(1, 4.0, new Date(), course, 1);
//        Grade grade3 = new Grade(1, 3.5, new Date(), course, 2);
//        Grade grade4 = new Grade(1, 2.5, new Date(), course, 2);
//        this.create(grade1);
//        this.create(grade2);
//        this.create(grade3);
//        this.create(grade4);
    }

    public Map<Integer, Grade> get(Integer student_index) {
        return grades.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getStudentIndex().equals(student_index))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Grade> get_all(){
        return grades;
    }

    public synchronized Grade create(Grade grade) {
        Integer current_counter = counter.incrementAndGet();
        grade.setId(current_counter);
        grades.put(current_counter, grade);
        return grades.get(current_counter);
    }

    public synchronized void delete_by_course(Course course){
        grades.values().removeIf(e -> e.getCourse().getId() == course.getId());
    }

}