import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

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
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Student> getStudents() {
        return studentService.getStudentsAsList();
    }

    @GET
    @Path("{student_index}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Student getStudent(@PathParam("student_index") Integer index) {
        Student student = studentService.getStudent(index);
        if(student == null){
            throw new NotFoundException();
        }
        return student;
    }

    @PUT
    @Path("{student_index}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void putStudent(@PathParam("student_index") Integer index, @Valid Student student){
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
}