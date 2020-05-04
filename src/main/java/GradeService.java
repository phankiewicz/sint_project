import com.mongodb.WriteResult;
import dev.morphia.Datastore;
import dev.morphia.Key;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeService {

    GradeDao gradeDao;
    Datastore database;

    public GradeService() {
        gradeDao = GradeDao.instance;
        database = new Model().get_database();
    }

    public Key<Grade> create(Grade grade) {
        return database.save(grade);
    }

    public Grade get_detail(Integer student_index, ObjectId id) {
        return database.get(Grade.class, id);
    }

    public List<Grade> get_list(Integer student_index) {
        return database.createQuery(Grade.class).asList();
    }


    synchronized UpdateResults update(Integer student_index, ObjectId id, Grade grade){
        Grade current_grade = database.get(Grade.class, id);

        final UpdateOperations<Grade> update_operations = database.createUpdateOperations(Grade.class);
        update_operations.set("value", grade.getValue());
        update_operations.set("date", grade.getDate());
        update_operations.set("course", grade.getCourse());

        return database.update(current_grade, update_operations);
    }

    synchronized public WriteResult delete(Integer student_index, ObjectId id) {
        return database.delete(Grade.class, id);
    }

}