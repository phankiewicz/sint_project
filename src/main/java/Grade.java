import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Grade {
    private int id;
    private double value;
    private Date date;
    private Course course;
    private Integer studentIndex;

    public Grade(){

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

    public void setValue(float value) {
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


}
