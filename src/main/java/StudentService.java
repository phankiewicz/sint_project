import com.mongodb.WriteResult;
import dev.morphia.Datastore;
import dev.morphia.Key;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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

    public List<Student> getStudents(String firstName, String lastName, Date birthday, int birthdayCompare) {
        Query<Student> query = database.createQuery(Student.class);
        if(firstName != null){
            query = query.field("firstName").containsIgnoreCase(firstName);
        }
        if(lastName != null){
            query = query.field("lastName").containsIgnoreCase(lastName);
        }
        if(birthday != null){
            if(birthdayCompare > 0){
                query = query.field("birthday").greaterThan(birthday);
            }
            else if(birthdayCompare < 0){
                query = query.field("birthday").lessThan(birthday);
            }
            else {
                query = query.field("birthday").equal(birthday);
            }
        }
        return query.asList();
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