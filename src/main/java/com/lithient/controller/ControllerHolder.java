package com.lithient.controller;

import java.util.concurrent.atomic.AtomicReference;

/**
 * simple static holder for a singleton Controller.
 * 
 * @author robert
 */
public final class ControllerHolder {
    /**
     * container for the Controller.
     */
    private static final AtomicReference<Controller> HOLDER = new AtomicReference<>();

    /**
     * private constructor to prevent instantiation.
     */
    private ControllerHolder() {

    }

    /**
     * inject a controller exactly once. subsequent calls with a non-null controller are ignored.
     * 
     * @param controller the controller to inject.
     */
    public static void setController(final Controller controller) {
        if (controller != null) {
            HOLDER.compareAndSet(null, controller);
        }
    }

    /**
     * get the current stored controller. Will return null if none has been injected.
     * 
     * @return the controller.
     */
    public static Controller getController() {
        return HOLDER.get();
    }

    /**
     * force the current holder to reset to empty. This is mainly for testing.
     */
    public static void reset() {
        HOLDER.set(null);
    }
}
