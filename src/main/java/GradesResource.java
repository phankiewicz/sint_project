import java.net.URI;
import java.net.URISyntaxException;
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
    public List<Grade> getStudents() {
        return gradeService.get_list(student_index);
    }

    @POST
    public Response create(@Valid Grade grade) throws URISyntaxException {
        if (!grade.is_valid()){
            throw new BadRequestException();
        }
        Grade created_grade = gradeService.create(grade);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + created_grade.getId())).build();
    }

}