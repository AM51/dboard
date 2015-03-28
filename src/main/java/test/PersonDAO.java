package test;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

/**
 * Created by archit.mittal on 16/01/15.
 */
public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Person> findById(String id){
        return Optional.fromNullable(get(id));
    }

    public void addRecord(String name,String email,DateTime birthday){
        persist(new Person(name,email,birthday));
    }
}
