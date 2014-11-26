package me.chengtx.webapp.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Root resource (exposed at "root" path)
 */
@Component
@Path("/")
public class RootResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getString() {
        return "Hello, CloudFoundry V2!";
    }

    @GET
    @Path("/sys")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getSysProp() {
        return System.getenv();
    }

}
