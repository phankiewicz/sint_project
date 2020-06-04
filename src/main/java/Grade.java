import com.fasterxml.jackson.annotation.JsonFormat;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import com.fasterxml.jackson.annotation.JsonFormat;
import dev.morphia.annotations.*;
import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity("grades")
@XmlRootElement
public class Grade {
    @InjectLinks({
            @InjectLink(resource = GradeResource.class, rel = "self", bindings = {@Binding(name = "grade_id", value = "${instance.id}")}),
            @InjectLink(resource = GradesResource.class, rel = "parent")
    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    @Transient
    List<Link> links;
    @XmlTransient
    @Id
    ObjectId object_id;
    private int id;
    private double value;
    @JsonFormat(shape=JsonFormat.Shape.STRING,
            pattern="yyyy-MM-dd", timezone="CET")
    private Date date;
    @Reference
    private Course course;
    @XmlTransient
    private Integer studentIndex;

    List<Double> value_values = Arrays.asList(2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0);

    public Grade(){

    }

    public Grade(double value, Date date, Course course, Integer studentIndex){
        this.value = value;
        this.date = date;
        this.course = course;
        this.studentIndex = studentIndex;
    }

    public Grade(int id, double value, Date date, Course course, Integer studentIndex){
        this.id = id;
        this.value = value;
        this.date = date;
        this.course = course;
        this.studentIndex = studentIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(Integer studentIndex) {
        this.studentIndex = studentIndex;
    }

    protected boolean is_valid(){
        if (this.getDate() == null || this.getDate().equals("") || !value_values.contains(this.getValue()) || this.getCourse() == null){
            return false;
        }
        return true;
    }
}
