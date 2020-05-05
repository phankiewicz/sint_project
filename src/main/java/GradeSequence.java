import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import javax.xml.bind.annotation.XmlTransient;

@Entity("grade_sequence")
public class GradeSequence {
    @XmlTransient
    @Id
    ObjectId _id;
    private int sequence;

    public GradeSequence(){}

    public GradeSequence(int sequence){
        this.sequence = sequence;
    }


    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
