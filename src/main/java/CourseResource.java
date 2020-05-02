import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/courses")
public class CourseResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    CourseService courseService;

    public CourseResource() {
        courseService = new CourseService();
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Course> getStudents() {
        return courseService.get_list();
    }

    @GET
    @Path("{course_id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Course getStudent(@PathParam("course_id") Integer id) {
        Course course = courseService.get_detail(id);
        if(course == null){
            throw new NotFoundException();
        }
        return course;
    }

    @POST
    public Response create(@Valid Course course) throws URISyntaxException {
        if (!course.is_valid()){
            throw new BadRequestException();
        }
        Course created_course = courseService.create(course);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + created_course.getId())).build();
    }

    @PUT
    @Path("{course_id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void update(@PathParam("course_id") Integer id, @Valid Course course){
        if (!course.is_valid()){
            throw new BadRequestException();
        }
        Course previous_course = courseService.update(id, course);
        if(previous_course == null){
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{course_id}")
    public void delete(@PathParam("course_id") Integer id) {
        Course course = courseService.delete(id);
        if(course == null){
            throw new NotFoundException();
        }
    }
}