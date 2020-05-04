import com.fasterxml.jackson.annotation.JsonCreator;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.util.Date;

public class Model {

    private final MongoClient client;
    private final Morphia morphia;
    private final Datastore database;

    public Datastore get_database(){
        return database;
    }


    public Model(){
        this.client = new MongoClient("localhost", 27017);
        this.morphia = new Morphia();
        this.morphia.map(Student.class, Course.class, Grade.class);
        this.database = this.morphia.createDatastore(this.client, "sint_project");
        this.database.ensureIndexes();
        this.database.enableDocumentValidation();

        initialize(this);
    };

    public static void initialize(Model model){
        Datastore db = model.get_database();
        if(db.createQuery(Student.class).count() > 0){
            return;
        }
        Student student1 = new Student(1, "Piotr", "Hankiewicz", new Date());
        Student student2 = new Student(2, "Jan", "Kowalski", new Date());

        Course course1 = new Course("course1", "teacher1");
        Course course2 = new Course("course2", "teacher2");

        Grade grade1 = new Grade(4.5, new Date(), course1, 1);
        Grade grade2 = new Grade(4.0, new Date(), course1, 1);
        Grade grade3 = new Grade(3.5, new Date(), course2, 2);
        Grade grade4 = new Grade(2.5, new Date(), course2, 2);

        db.save(student1);
        db.save(student2);

        db.save(course1);
        db.save(course2);

        db.save(grade1);
        db.save(grade2);
        db.save(grade3);
        db.save(grade4);
    }

}