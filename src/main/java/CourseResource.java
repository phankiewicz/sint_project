import com.mongodb.WriteResult;
import dev.morphia.Key;
import dev.morphia.query.UpdateResults;
import org.bson.types.ObjectId;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
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
    public List<Course> getStudents(@QueryParam("name") String name,
                                    @QueryParam("lecturer") String lecturer) {
        return courseService.get_list(name, lecturer);
    }

    @GET
    @Path("{course_id}")
    public Course getStudent(@PathParam("course_id") ObjectId id) {
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
        Key<Course> created_course = courseService.create(course);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + created_course.getId())).build();
    }

    @PUT
    @Path("{course_id}")
    public void update(@PathParam("course_id") ObjectId id, @Valid Course course){
        if (!course.is_valid()){
            throw new BadRequestException();
        }
        UpdateResults updateResults = courseService.update(id, course);
        if(updateResults.getUpdatedCount() == 0){
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{course_id}")
    public void delete(@PathParam("course_id") ObjectId id) {
        WriteResult write_result = courseService.delete(id);
        if(write_result.getN() == 0){
            throw new NotFoundException();
        }
    }
}