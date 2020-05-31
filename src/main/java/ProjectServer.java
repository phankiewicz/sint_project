import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ProjectServer {
    public static void main(String[] args) throws Exception {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        ResourceConfig config = new ResourceConfig();
        config.registerClasses(StudentsResource.class, StudentResource.class, CourseResource.class);
        config.register(DeclarativeLinkingFeature.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        server.start();
    }
}
