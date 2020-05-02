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


    public Grade update(Integer student_index, Integer id, Grade grade){
        grade.setId(id);
        return gradeDao.get(student_index).put(id, grade);
    }

    public Grade delete(Integer student_index, Integer id) {
        return gradeDao.get(student_index).remove(id);
    }

}