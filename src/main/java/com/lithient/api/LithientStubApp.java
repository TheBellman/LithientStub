package com.lithient.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * our application definition.
 * 
 * @author robert
 */
@ApplicationPath("/")
public class LithientStubApp extends ResourceConfig {
    /**
     * primary constructor.
     */
    public LithientStubApp() {
        register(CollectorService.class);
        register(StatusService.class);
        register(JacksonFeature.class);
    }
}
