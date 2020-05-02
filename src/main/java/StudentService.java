import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentService {

    StudentDao studentDao;

    public StudentService() {
        studentDao = StudentDao.instance;
    }

    public Student createStudent(Student student) {
        return studentDao.createStudent(student);
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

    public Student updateStudent(Integer index, Student student){
        student.setIndex(index);
        return studentDao.getStudents().put(index, student);

    }

    public Student deleteStudent(Integer id) {
        return studentDao.getStudents().remove(id);
    }

}