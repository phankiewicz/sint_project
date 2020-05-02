import java.net.URI;
import java.net.URISyntaxException;
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
    public List<Grade> getStudents() {
        return gradeService.get_list(student_index);
    }

    @GET
    @Path("{grade_id}")
    public Grade getStudent(@PathParam("grade_id") Integer id) {
        Grade grade = gradeService.get_detail(student_index, id);
        if(grade == null){
            throw new NotFoundException();
        }
        return grade;
    }

    @POST
    public Response create(@Valid Grade grade) throws URISyntaxException {
        if (!grade.is_valid()){
            throw new BadRequestException();
        }
        Grade created_grade = gradeService.create(grade);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + created_grade.getId())).build();
    }

    @PUT
    @Path("{grade_id}")
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
    @Path("{grade_id}")
    public void delete(@PathParam("grade_id") Integer id) {
        Grade grade = gradeService.delete(student_index, id);
        if(grade == null){
            throw new NotFoundException();
        }
    }
}