import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Course {
    private int id;
    private String name;
    private String lecturer;

    public Course(){

    }

    public Course(int id, String name, String lecturer) {
        super();
        this.id = id;
        this.name = name;
        this.lecturer = lecturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
