import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import javax.xml.bind.annotation.XmlTransient;

@Entity("student_sequence")
public class StudentSequence {
    @XmlTransient
    @Id
    ObjectId _id;
    private int sequence;

    public StudentSequence(){}

    public StudentSequence(int sequence){
        this.sequence = sequence;
    }


    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
