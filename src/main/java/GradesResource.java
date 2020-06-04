import com.mongodb.WriteResult;
import dev.morphia.Key;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateResults;
import org.bson.types.ObjectId;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/students/{student_index}/grades")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class GradesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    GradeService gradeService;

    public GradesResource() {
        gradeService = new GradeService();
    }

    @PathParam("student_index")
    public int student_index;

    @GET
    public List<Grade> getStudents(
            @QueryParam("value") double value,
            @QueryParam("valueCompare") int valueCompare,
            @QueryParam("date") Date date,
            @QueryParam("dateCompare") int dateCompare,
            @QueryParam("course") String course_id) {
        return gradeService.get_list(student_index, value, valueCompare, date, dateCompare, course_id);
    }

    @POST
    public Response create(@Valid Grade grade) throws URISyntaxException {
        Course course = gradeService.database.get(Course.class, grade.getCourse().getId());
        if (!grade.is_valid() || course == null){
            throw new BadRequestException();
        }
        grade.setCourse(course);
        grade.setStudentIndex(student_index);
        Key<Grade> created_grade = gradeService.create(grade);
        Grade current_grade = gradeService.database.get(Grade.class, created_grade.getId());
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + current_grade.getId())).build();
    }
}