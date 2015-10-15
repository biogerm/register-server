package resources;

import core.User;
import db.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by biogerm on 15/10/13.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    @UnitOfWork
    public User createUser(User user) {
        return userDAO.create(user);
    }

    @GET
    @UnitOfWork
    public User get(@Auth User user) {
        return userDAO.getUser(user.getUsername());
    }

    @PUT
    @Path("{username}")
    @UnitOfWork
    public User updateLoginTime(@Auth User authUser, @PathParam("username") String username) {
        if(!authUser.getUsername().equals(username)) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        User user = userDAO.getUser(username);
        Date[] dates = new Date[5];
        dates[0] = user.getLogin1();
        dates[1] = user.getLogin2();
        dates[2] = user.getLogin3();
        dates[3] = user.getLogin4();
        dates[4] = user.getLogin5();
        int smallest = 0;
        Date temp = dates[0];
        for(int i = 0; i < 5; i++) {
            if(null != dates[i]) {
                if (dates[i].compareTo(temp) < 0) {
                    smallest = i;
                    temp = dates[i];
                }
            } else {
                smallest = i;
                break;
            }
        }
        dates[smallest] = new Date();
        user.setLogin1(dates[0]);
        user.setLogin2(dates[1]);
        user.setLogin3(dates[2]);
        user.setLogin4(dates[3]);
        user.setLogin5(dates[4]);
        return userDAO.updateLoginTimestamp(user);
    }

}
