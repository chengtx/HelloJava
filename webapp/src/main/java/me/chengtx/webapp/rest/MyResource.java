package me.chengtx.webapp.rest;

import me.chengtx.webapp.jpa.UserDAO;
import me.chengtx.webapp.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUser() {
        UserDAO userDao = new UserDAO();
        return userDao.getAllUsers();
    }

    @GET
    @Path("/sys")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getSysProp() {
        return System.getenv();
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String getString() {
        return "Hello, CloudFoundry V2!";
    }


}
