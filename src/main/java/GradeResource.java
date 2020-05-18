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
    public List<Grade> getStudents(
            @QueryParam("value") double value,
            @QueryParam("valueCompare") int valueCompare,
            @QueryParam("date") Date date,
            @QueryParam("dateCompare") int dateCompare) {
        return gradeService.get_list(student_index, value, valueCompare, date, dateCompare);
    }

    @GET
    @Path("{grade_id}")
    public Grade getStudent(@PathParam("grade_id") int id) {
        Grade grade = gradeService.get_detail(student_index, id);
        if(grade == null){
            throw new NotFoundException();
        }
        return grade;
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
        System.out.println(current_grade.getIndex());
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + current_grade.getIndex())).build();
    }

    @PUT
    @Path("{grade_id}")
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
    @Path("{grade_id}")
    public void delete(@PathParam("grade_id") int id) {
        WriteResult write_result = gradeService.delete(student_index, id);
        if(write_result.getN() == 0){
            throw new NotFoundException();
        }
    }
}