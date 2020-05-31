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
@Path("/courses/{course_id}")
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
    public Course getStudent(@PathParam("course_id") ObjectId id) {
        Course course = courseService.get_detail(id);
        if(course == null){
            throw new NotFoundException();
        }
        return course;
    }

    @PUT
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
    public void delete(@PathParam("course_id") ObjectId id) {
        WriteResult write_result = courseService.delete(id);
        if(write_result == null){
            throw new NotFoundException();
        }
    }
}