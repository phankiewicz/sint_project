import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;


@XmlRootElement
public class Student {
    @InjectLinks({
    @InjectLink(resource = StudentResource.class, rel = "self", bindings = {@Binding(name = "student_index", value = "${instance.index}")}),
    @InjectLink(resource = StudentsResource.class, rel = "parent")
})
@XmlElement(name="link")
@XmlElementWrapper(name = "links")
@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;
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
