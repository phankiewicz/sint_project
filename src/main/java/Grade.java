import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Grade {
    private int id;
    private double value;
    private Date date;
    private Course course;
    private Integer studentIndex;

    List<Double> value_values = Arrays.asList(2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0);

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

    public boolean is_valid(){
        if (this.getDate() == null || this.getDate().equals("") || !value_values.contains(this.getValue()) || this.getCourse() == null || !CourseDao.instance.get().containsKey(this.getCourse().getId())){
            return false;
        }
        return true;
    }


}
