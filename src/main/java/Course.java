import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@Entity("courses")
@XmlRootElement
public class Course {
    @XmlTransient
    @Id
    ObjectId _id;
    private String name;
    private String lecturer;

    public Course(){

    }

    public Course(String name, String lecturer) {
        super();
        this.name = name;
        this.lecturer = lecturer;
    }

    @XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
    public ObjectId getId() {
        return _id;
    }
    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    protected boolean is_valid(){
        if (this.getName() == null || this.getName().equals("") || this.getLecturer() == null || this.getLecturer().equals("")){
            return false;
        }
        return true;
    }
}
