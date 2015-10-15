package db;

import com.google.common.base.Optional;
import core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * UserDAO provides DB access for User DAO
 *
 * Created by biogerm on 15/10/12.
 */
public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Create a new User in DB from User Entity
     *
     * @param user the user to be created
     * @return created user
     */
    public User create(User user) {
        if (null == getUser(user.getUsername())) {
            return persist(user);
        } else {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
    }

    /**
     * Query a User Entity from DB
     *
     * @param username the unique username to be queried
     * @return User entity
     */
    public User getUser(String username) {
        return uniqueResult(criteria().add(Restrictions.eq("username", username)));
    }

    /**
     * Update a User in DB
     *
     * @param newUser new User Entity to be updated
     * @return updated User
     */
    public User updateLoginTimestamp(User newUser) {
        return persist(newUser);
    }

    public List<User> findAll() {
        return list(namedQuery("core.User.findAll"));
    }
}
