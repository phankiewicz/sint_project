import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();
        if (e instanceof WebApplicationException)
            return ((WebApplicationException) e).getResponse();
        e.printStackTrace();
        return Response.status(500).entity(e.toString()).build();
    }
}
