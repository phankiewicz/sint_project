import dev.morphia.Datastore;
import dev.morphia.Key;

import java.util.List;

public class StudentService {

    StudentDao studentDao;
    Datastore database;

    public StudentService() {
        studentDao = StudentDao.instance;
        database = new Model().get_database();
    }

    public Key<Student> createStudent(Student student) {
        return database.save(student);
    }

    public Student getStudent(Integer id) {
        return database.createQuery(Student.class).field("index").equal(id).first();
    }

    public List<Student> getStudents() {
        return database.createQuery(Student.class).asList();
    }

    public Student updateStudent(Integer index, Student student){
        student.setIndex(index);
        return studentDao.getStudents().put(index, student);

    }

    public Student deleteStudent(Integer id) {
        return studentDao.getStudents().remove(id);
    }

}