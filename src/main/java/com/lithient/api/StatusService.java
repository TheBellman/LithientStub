package com.lithient.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lithient.Status;
import com.lithient.controller.ControllerHolder;

/**
 * small service to report the service status.
 * 
 * @author robert
 */
@Path("/status")
public final class StatusService {

    /**
     * retrieve the current status.
     * 
     * @return the current system status.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Status getStatus() {
        return ControllerHolder.getController().getStatus();
    }

}
