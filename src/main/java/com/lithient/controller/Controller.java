package com.lithient.controller;

import com.lithient.Status;

/**
 * definition of the core controller in our model-view-controller pattern.
 * 
 * @author robert
 */
public interface Controller {
    /**
     * report the status of the service.
     * 
     * @return the status of the service.
     */
    Status getStatus();

    /**
     * set the service to be active.
     */
    void activate();

    /**
     * set the service to be inactive.
     * TODO: nothing is firing this yet.
     */
    void shutdown();
}
