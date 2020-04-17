import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentService {

    StudentDao studentDao;

    public StudentService() {
        studentDao = StudentDao.instance;
    }

    public void createStudent(Student student) {
        studentDao.getStudents().put(student.getIndex(), student);
    }

    public Student getStudent(Integer id) {
        return studentDao.getStudents().get(id);
    }

    public Map<Integer, Student> getStudents() {
        return studentDao.getStudents();
    }

    public List<Student> getStudentsAsList() {
        List<Student> studentsList = new ArrayList<Student>(studentDao.getStudents().values());
        return studentsList;
    }

    public Student deleteStudent(Integer id) {
        return studentDao.getStudents().remove(id);
    }

}