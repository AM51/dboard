package test;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;

/**
 * Created by archit.mittal on 16/01/15.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    private final HibernateBundle<HelloWorldConfiguration> hibernateBundle = new HibernateBundle<HelloWorldConfiguration>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration helloWorldConfiguration) {
            return helloWorldConfiguration.getDatabase();
        }
    };
    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        final PersonDAO dao= new PersonDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new PersonResource(dao));
    }
}
