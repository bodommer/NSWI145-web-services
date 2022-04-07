package cz.cuni.mff.webservices.rest;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("service")
public class RestService extends ResourceConfig {
    public RestService() {
        packages("cz.cuni.mff.webservices.rest");
    }
}
