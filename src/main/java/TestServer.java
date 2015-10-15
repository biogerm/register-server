import auth.TestAuthenticator;
import config.DbConfiguration;
import core.User;
import db.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.TestResource;
import resources.UserResource;

/**
 * Register Server web application
 *
 * Created by biogerm on 15/9/24.
 */
public class TestServer extends Application<DbConfiguration> {
    public static void main(String[] args) throws Exception {
        new TestServer().run(args);
    }

    private final TestResource resource = new TestResource(); // "hello world" test
    private final HibernateBundle<DbConfiguration> hibernate = new HibernateBundle<DbConfiguration>(User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DbConfiguration dbConfiguration) {
            return dbConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<DbConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DbConfiguration configuration, Environment environment) throws Exception {
        final UserDAO dao = new UserDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserResource(dao));
        environment.jersey().register(resource); // for "hello, world" test
        environment.jersey().register(
                AuthFactory.binder(
                        new BasicAuthFactory<>(
                                new TestAuthenticator(dao),
                                "REGISTERATION IS REQUIRED",
                                User.class
                        )
                )
        );
    }
}
