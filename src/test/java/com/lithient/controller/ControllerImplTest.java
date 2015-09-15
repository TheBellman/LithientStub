package com.lithient.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ControllerImplTest {

    private Controller instance;

    @Before
    public void setUp() throws Exception {
        instance = new ControllerImpl();
    }

    @Test
    public void testGetStatus() {
        assertNotNull(instance.getStatus());
        assertEquals("inactive", instance.getStatus().getStatus());
    }

    @Test
    public void testActivate() {
        assertNotNull(instance.getStatus());
        assertEquals("inactive", instance.getStatus().getStatus());
        instance.activate();
        assertEquals("active", instance.getStatus().getStatus());
    }

}
