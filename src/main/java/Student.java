import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement
public class Student {
    private int index;
    private String firstName;
    private String lastName;
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
