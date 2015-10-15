package auth;

import com.google.common.base.Optional;
import core.User;
import db.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceException;

public class TestAuthenticator implements Authenticator<BasicCredentials, User> {
    private final UserDAO userDAO;

    public TestAuthenticator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        if(null == userDAO.getUser(basicCredentials.getUsername())) {
            return Optional.absent();
        }
        if(getPassword(basicCredentials.getUsername()).equals(basicCredentials.getPassword())) {
            return Optional.of(new User(basicCredentials.getUsername(), basicCredentials.getPassword()));
        } else {
            return Optional.absent();
        }
    }

    private String getPassword(String username) {
        User user = userDAO.getUser(username);
        System.out.println("Auth " + user.getUsername());
        return user.getPassword();
    }
}
