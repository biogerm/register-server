package resources;

import core.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by biogerm on 15/9/24.
 */
@Path("/")
public class TestResource {


    @GET
    @UnitOfWork
    public String sayHello(@Auth User user) {
        return "hello!";
    }
}
