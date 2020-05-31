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
public class CoursesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    CourseService courseService;

    public CoursesResource() {
        courseService = new CourseService();
    }

    @GET
    public List<Course> getStudents() {
        return courseService.get_list();
    }

    @POST
    public Response create(@Valid Course course) throws URISyntaxException {
        if (!course.is_valid()){
            throw new BadRequestException();
        }
        Key<Course> created_course = courseService.create(course);
        return Response.created(URI.create(uriInfo.getAbsolutePath() + "/" + created_course.getId())).build();
    }


}