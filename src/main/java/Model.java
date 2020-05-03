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
        this.morphia.map(Student.class);
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

        db.save(student1);
        db.save(student2);
    }

}