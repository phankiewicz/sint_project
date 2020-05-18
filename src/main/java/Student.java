import com.fasterxml.jackson.annotation.JsonFormat;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;


@Entity("students")
@XmlRootElement
public class Student {
    @XmlTransient
    @Id
    ObjectId _id;
    private int index;
    private String firstName;
    private String lastName;
    @JsonFormat(shape=JsonFormat.Shape.STRING,
            pattern="yyyy-MM-dd", timezone="CET")
    private Date birthday;

    public Student(){

    }

    public Student(int index, String firstName, String lastName, Date dateOfBirth) {
        super();
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = dateOfBirth;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date dateOfBirth) {
        this.birthday = dateOfBirth;
    }

    protected boolean is_valid(){
        if (this.getBirthday() == null || this.getFirstName().equals("") || this.getFirstName() == null || this.getLastName().equals("") || this.getLastName() == null){
            return false;
        }
        return true;
    }
}
