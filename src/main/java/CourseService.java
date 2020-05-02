import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseService {

    CourseDao courseDao;

    public CourseService() {
        courseDao = CourseDao.instance;
    }

    public Course create(Course course) {
        return courseDao.create(course);
    }

    public Course get_detail(Integer id) {
        return courseDao.get().get(id);
    }

    public List<Course> get_list() {
        List<Course> courses_list = new ArrayList<Course>(courseDao.get().values());
        return courses_list;
    }


    public Course update(Integer id, Course course){
        course.setId(id);
        return courseDao.get().put(id, course);
    }

    public Course delete(Integer id) {
        Course course = courseDao.get().remove(id);
        GradeDao.instance.delete_by_course(course);
        return course;
    }

}