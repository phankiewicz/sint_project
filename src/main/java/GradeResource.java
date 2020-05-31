import java.net.URI;
import java.net.URISyntaxException;
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
    public Grade getStudent(@PathParam("grade_id") Integer id) {
        Grade grade = gradeService.get_detail(student_index, id);
        if(grade == null){
            throw new NotFoundException();
        }
        return grade;
    }

    @PUT
    public void update(@PathParam("grade_id") Integer id, @Valid Grade grade){
        if (!grade.is_valid()){
            throw new BadRequestException();
        }
        Grade previous_grade = gradeService.update(student_index, id, grade);
        if(previous_grade == null){
            throw new NotFoundException();
        }
    }

    @DELETE
    public void delete(@PathParam("grade_id") Integer id) {
        Grade grade = gradeService.delete(student_index, id);
        if(grade == null){
            throw new NotFoundException();
        }
    }
}