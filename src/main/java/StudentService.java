import com.mongodb.WriteResult;
import dev.morphia.Datastore;
import dev.morphia.Key;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;

import java.util.List;

public class StudentService {

    Datastore database;

    public StudentService() {
        database = new Model().get_database();
    }

    public Key<Student> createStudent(Student student) {
        int index = database.findAndModify(database.createQuery(StudentSequence.class), database.createUpdateOperations(StudentSequence.class).inc("sequence")).getSequence();
        student.setIndex(index);
        return database.save(student);
    }

    public Student getStudent(Integer index) {
        return database.createQuery(Student.class).field("index").equal(index).first();
    }

    public List<Student> getStudents() {
        return database.createQuery(Student.class).asList();
    }

    public UpdateResults updateStudent(Integer index, Student student){
        student.setIndex(index);
        Student current_student = database.createQuery(Student.class).field("index").equal(index).first();

        final UpdateOperations<Student> update_operations = database.createUpdateOperations(Student.class);
        update_operations.set("firstName", student.getFirstName());
        update_operations.set("lastName", student.getLastName());
        update_operations.set("birthday", student.getBirthday());

        return database.update(current_student, update_operations);

    }

    public WriteResult deleteStudent(Integer id) {
        Student student = database.createQuery(Student.class).field("index").equal(id).first();
        if(student == null){
            return null;
        }
        return database.delete(student);
    }

}