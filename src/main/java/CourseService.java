import com.mongodb.WriteResult;
import dev.morphia.Datastore;
import dev.morphia.Key;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.UpdateResults;
import org.bson.types.ObjectId;

import java.util.List;

public class CourseService {

    Datastore database;

    public CourseService() {
        database = new Model().get_database();
    }

    public Key<Course> create(Course course) {
        return database.save(course);
    }

    public Course get_detail(ObjectId id) {
        return database.get(Course.class, id);
    }

    public List<Course> get_list() {
        return database.createQuery(Course.class).asList();
    }


    public UpdateResults update(ObjectId id, Course course){
        Course current_course = database.get(Course.class, id);

        final UpdateOperations<Course> update_operations = database.createUpdateOperations(Course.class);
        update_operations.set("name", course.getName());
        update_operations.set("lecturer", course.getLecturer());

        return database.update(current_course, update_operations);
    }

    public WriteResult delete(ObjectId id) {
        Course course = database.get(Course.class, id);
        List<Grade> grades = database.createQuery(Grade.class).filter("course", course).asList();
        for(Grade grade: grades){
            Grade current_grade = database.createQuery(Grade.class).field("index").equal(grade.getIndex()).first();
            database.delete(current_grade);
        }
        return database.delete(Course.class, id);
    }

}