import com.mongodb.WriteResult;
import dev.morphia.Datastore;
import dev.morphia.Key;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GradeService {

    Datastore database;

    public GradeService() {
        database = new Model().get_database();
    }

    public Key<Grade> create(Grade grade) {
        int id = database.findAndModify(database.createQuery(GradeSequence.class), database.createUpdateOperations(GradeSequence.class).inc("sequence")).getSequence();
        grade.setIndex(id);
        return database.save(grade);
    }

    public Grade get_detail(Integer student_index, int id) {
        return database.createQuery(Grade.class).field("studentIndex").equal(student_index).field("index").equal(id).first();
    }

    public List<Grade> get_list(Integer student_index, double value, int valueCompare, Date date, int dateCompare) {
        Query<Grade> query = database.createQuery(Grade.class).field("studentIndex").equal(student_index);
        if(value != 0){
            if(valueCompare > 0){
                query = query.field("value").greaterThan(value);
            }
            else if(valueCompare < 0){
                query = query.field("value").lessThan(value);
            }
            else {
                query = query.field("value").equal(value);
            }
        }
        if(date != null){
            if(dateCompare > 0){
                query = query.field("date").greaterThan(date);
            }
            else if(dateCompare < 0){
                query = query.field("date").lessThan(date);
            }
            else {
                query = query.field("date").equal(date);
            }
        }
        return query.asList();
    }


    synchronized UpdateResults update(Integer student_index, int id, Grade grade){
        Grade current_grade = database.createQuery(Grade.class).field("index").equal(id).first();

        final UpdateOperations<Grade> update_operations = database.createUpdateOperations(Grade.class);
        update_operations.set("value", grade.getValue());
        update_operations.set("date", grade.getDate());
        update_operations.set("course", grade.getCourse());

        return database.update(current_grade, update_operations);
    }

    synchronized public WriteResult delete(Integer student_index, int id) {
        Grade current_grade = database.createQuery(Grade.class).field("index").equal(id).first();
        return database.delete(current_grade);
    }

}