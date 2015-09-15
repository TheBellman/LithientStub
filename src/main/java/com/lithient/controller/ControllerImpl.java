package com.lithient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lithient.Status;

/**
 * implementation of our controller - there should only be one of these in play, but it is the responsibility of someone else to
 * handle that.
 * 
 * @author robert
 */
public final class ControllerImpl implements Controller {
    /**
     * the system status.
     */
    private final Status status = new Status("inactive");
    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void activate() {
        status.setStatus("active");
        // TODO we should stop this cleanly
        RateReporter.start(this);
        LOGGER.info("Service started");
    }

    @Override
    public void shutdown() {
        RateReporter.stop();
        status.setStatus("stopped");
    }

}
