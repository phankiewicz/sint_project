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

@Path("/students/{student_index}/grades/{grade_id}")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class GradeResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    GradeService gradeService;

    public GradeResource() {
        gradeService = new GradeService();
    }

    @PathParam("student_index")
    public int student_index;

    @GET
    public Grade getStudent(@PathParam("grade_id") int id) {
        Grade grade = gradeService.get_detail(student_index, id);
        if(grade == null){
            throw new NotFoundException();
        }
        return grade;
    }

    @PUT
    public void update(@PathParam("grade_id") int id, @Valid Grade grade){
        Course course = gradeService.database.get(Course.class, grade.getCourse().getId());
        if (!grade.is_valid() || course == null){
            throw new BadRequestException();
        }
        grade.setCourse(course);
        UpdateResults updateResults = gradeService.update(student_index, id, grade);
        if(updateResults.getUpdatedCount() == 0){
            throw new NotFoundException();
        }
    }

    @DELETE
    public void delete(@PathParam("grade_id") int id) {
        WriteResult write_result = gradeService.delete(student_index, id);
        if(write_result == null){
            throw new NotFoundException();
        }
    }
}