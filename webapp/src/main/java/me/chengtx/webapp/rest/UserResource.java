package me.chengtx.webapp.rest;

import me.chengtx.webapp.dao.UserStore;
import me.chengtx.webapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * User resource (exposed at "users" path)
 */
@Component
@Path("/users")
public class UserResource {

    @Autowired
    private UserStore userStore;

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUser() {

        List<User> userList = userStore.getAllUsers();
        userList.forEach(s -> {
            System.out.println(s.getUid());
            System.out.println(s.getName());
            System.out.println(s.getEmail());
        });

        return userList;
    }

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    public String getString() {
        userStore.createUser();
        return "Hello, CloudFoundry V2!";
    }

}
