import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/students/{student_index}")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class StudentResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    StudentService studentService;

    public StudentResource() {
        studentService = new StudentService();
    }

    @GET
    public Student getStudent(@PathParam("student_index") Integer index) {
        Student student = studentService.getStudent(index);
        if(student == null){
            throw new NotFoundException();
        }
        return student;
    }

    @PUT
    public void putStudent(@PathParam("student_index") Integer index, @Valid Student student){
        if (!student.is_valid()){
            throw new BadRequestException();
        }
        Student previous_student = studentService.updateStudent(index, student);
        if(previous_student == null){
            throw new NotFoundException();
        }
    }

    @DELETE
    public void deleteStudent(@PathParam("student_index") Integer index) {
        Student student = studentService.deleteStudent(index);
        if(student == null){
            throw new NotFoundException();
        }
    }

    @Path("/grades")
    public Class<GradeResource> getGradeResource() {
        return GradeResource.class;
    }
}