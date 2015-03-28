package test;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.sun.jersey.api.NotFoundException;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by archit.mittal on 16/01/15.
 */
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private final PersonDAO peopleDAO;

    public PersonResource(PersonDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GET
    @Path("/{personId}")
    @UnitOfWork
    public Person getPerson(@PathParam("personId")String personId){
        return findSafely(personId);
    }

    private Person findSafely(String personId) {
        final Optional<Person> person = peopleDAO.findById(personId);
        if(!person.isPresent()){
            throw new NotFoundException("not found");
        }
        return person.get();
    }

    @POST
    @UnitOfWork
    public void addPerson(PostRequest request){
        peopleDAO.addRecord(request.getName(), request.getEmail(), request.getBirthday());
    }
}
