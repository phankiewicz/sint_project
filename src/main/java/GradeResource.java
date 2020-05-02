import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

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
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Grade> getStudents() {
        return gradeService.get_list(student_index);
    }

    @GET
    @Path("{grade_id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Grade getStudent(@PathParam("grade_id") Integer id) {
        Grade grade = gradeService.get_detail(student_index, id);
        if(grade == null){
            throw new NotFoundException();
        }
        return grade;
    }

    @POST
    public Response create(@Valid Grade grade) throws URISyntaxException {
        Grade created_grade = gradeService.create(grade);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + created_grade.getId())).build();
    }

    @PUT
    @Path("{grade_id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void update(@PathParam("grade_id") Integer id, @Valid Grade grade){
        Grade previous_grade = gradeService.update(1, id, grade);
        if(previous_grade == null){
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{grade_id}")
    public void delete(@PathParam("grade_id") Integer id) {
        Grade grade = gradeService.delete(1, id);
        if(grade == null){
            throw new NotFoundException();
        }
    }
}