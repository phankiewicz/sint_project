import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/students")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class StudentsResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    StudentService studentService;

    public StudentsResource() {
        studentService = new StudentService();
    }

    @GET
    public List<Student> getStudents() {
        return studentService.getStudentsAsList();
    }

    @POST
    public Response createStudent(Student student) throws URISyntaxException {
        if (!student.is_valid()){
            throw new BadRequestException();
        }
        Student created_student = studentService.createStudent(student);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + String.valueOf(student.getIndex()))).build();
    }
}