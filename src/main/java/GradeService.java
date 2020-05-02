import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeService {

    GradeDao gradeDao;

    public GradeService() {
        gradeDao = GradeDao.instance;
    }

    public Grade create(Grade grade) {
        return gradeDao.create(grade);
    }

    public Grade get_detail(Integer student_index, Integer id) {
        return gradeDao.get(student_index).get(id);
    }

    public List<Grade> get_list(Integer student_index) {
        return new ArrayList<Grade>(gradeDao.get(student_index).values());
    }


    synchronized Grade update(Integer student_index, Integer id, Grade grade){
        Grade current_grade = this.get_detail(student_index, id);
        if(current_grade == null)
        {
            return current_grade;
        }
        grade.setId(id);
        Course course = CourseDao.instance.get().get(grade.getCourse().getId());
        grade.setCourse(course);
        return gradeDao.get_all().put(id, grade);
    }

    synchronized public Grade delete(Integer student_index, Integer id) {
        Grade grade = this.get_detail(student_index, id);
        if(grade == null)
        {
            return grade;
        }
        return gradeDao.get_all().remove(id);
    }

}