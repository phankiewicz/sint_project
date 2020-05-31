import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Transient;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@Entity("courses")
@XmlRootElement
public class Course {
    @InjectLinks({
            @InjectLink(resource = CourseResource.class, rel = "self", bindings = {@Binding(name = "course_id", value = "${instance.id}")}),
            @InjectLink(resource = CoursesResource.class, rel = "parent")
    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    @Transient
    List<Link> links;
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
