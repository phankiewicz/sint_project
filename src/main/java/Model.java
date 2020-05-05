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

        StudentSequence student_sequence = new StudentSequence(0);
        db.save(student_sequence);
        GradeSequence grade_sequence = new GradeSequence(0);
        db.save(grade_sequence);

        int index = 0;
        index = db.findAndModify(db.createQuery(StudentSequence.class), db.createUpdateOperations(StudentSequence.class).inc("sequence")).getSequence();
        Student student1 = new Student(index, "Piotr", "Hankiewicz", new Date());
        index = db.findAndModify(db.createQuery(StudentSequence.class), db.createUpdateOperations(StudentSequence.class).inc("sequence")).getSequence();
        Student student2 = new Student(index, "Jan", "Kowalski", new Date());

        Course course1 = new Course("course1", "teacher1");
        Course course2 = new Course("course2", "teacher2");

        int grade_id;
        grade_id = db.findAndModify(db.createQuery(GradeSequence.class), db.createUpdateOperations(GradeSequence.class).inc("sequence")).getSequence();
        Grade grade1 = new Grade(grade_id,4.5, new Date(), course1, student1.getIndex());
        grade_id = db.findAndModify(db.createQuery(GradeSequence.class), db.createUpdateOperations(GradeSequence.class).inc("sequence")).getSequence();
        Grade grade2 = new Grade(grade_id,4.0, new Date(), course1, student1.getIndex());
        grade_id = db.findAndModify(db.createQuery(GradeSequence.class), db.createUpdateOperations(GradeSequence.class).inc("sequence")).getSequence();
        Grade grade3 = new Grade(grade_id,3.5, new Date(), course2, student2.getIndex());
        grade_id = db.findAndModify(db.createQuery(GradeSequence.class), db.createUpdateOperations(GradeSequence.class).inc("sequence")).getSequence();
        Grade grade4 = new Grade(grade_id,2.5, new Date(), course2, student2.getIndex());

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