import com.mongodb.WriteResult;
import dev.morphia.Key;
import dev.morphia.query.UpdateResults;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
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
    public List<Student> getStudents(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("birthday") Date birthday,
            @DefaultValue("0") @QueryParam("birthdayCompare") int birthdayCompare) {
        return studentService.getStudents(firstName, lastName, birthday, birthdayCompare);
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
    public Response createStudent(Student student) throws URISyntaxException {
        if (!student.is_valid()){
            throw new BadRequestException();
        }
        Key<Student> created_student = studentService.createStudent(student);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + String.valueOf(student.getIndex()))).build();
    }

    @PUT
    @Path("{student_index}")
    public void putStudent(@PathParam("student_index") Integer index, @Valid Student student){
        if (!student.is_valid()){
            throw new BadRequestException();
        }
        UpdateResults updateResults = studentService.updateStudent(index, student);
        if(updateResults.getUpdatedCount() == 0){
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{student_index}")
    public void deleteStudent(@PathParam("student_index") Integer index) {
        WriteResult write_result = studentService.deleteStudent(index);
        if(write_result == null){
            throw new NotFoundException();
        }
    }
}