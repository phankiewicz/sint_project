import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Path("/students")
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

    @GET
    @Path("{student_index}")
    public Student getStudent(@PathParam("student_index") Integer index) {
        Student student = studentService.getStudent(index);
        if(student == null){
            throw new NotFoundException();
        }
        return student;
    }

    @POST
    public Response createStudent(@Valid Student student) throws URISyntaxException {
        System.out.println(student.is_valid());
        if (!student.is_valid()){
            throw new BadRequestException();
        }
        Student created_student = studentService.createStudent(student);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + String.valueOf(student.getIndex()))).build();
    }

    @PUT
    @Path("{student_index}")
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
    @Path("{student_index}")
    public void deleteStudent(@PathParam("student_index") Integer index) {
        Student student = studentService.deleteStudent(index);
        if(student == null){
            throw new NotFoundException();
        }
    }

    @Path("{student_index}/grades")
    public Class<GradeResource> getGradeResource() {
        return GradeResource.class;
    }
}