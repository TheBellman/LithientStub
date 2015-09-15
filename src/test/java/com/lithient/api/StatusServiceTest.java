package com.lithient.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lithient.Status;
import com.lithient.controller.Controller;
import com.lithient.controller.ControllerHolder;

public class StatusServiceTest {
    @Mock
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ControllerHolder.reset();
        ControllerHolder.setController(controller);
        when(controller.getStatus()).thenReturn(new Status("testmode"));
    }

    @Test
    public void testGetStatus() {
        StatusService instance = new StatusService();
        assertEquals("testmode", instance.getStatus().getStatus());
    }

}
