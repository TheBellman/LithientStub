package com.lithient;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lithient.Status;

public class StatusTest {

    @Test
    public void test() {
        Status instance = new Status("test");
        assertEquals("Status[status=test,events=0,clicks=0]", instance.toString());
    }

    @Test
    public void testUpdate() {
        Status instance = new Status("test");
        instance.setStatus("update");
        assertEquals("Status[status=update,events=0,clicks=0]", instance.toString());
    }

    @Test
    public void testClicks() {
        Status instance = new Status("test");
        instance.addClick();
        instance.addClick();
        instance.addClick();
        assertEquals("Status[status=test,events=0,clicks=3]", instance.toString());
        assertEquals(3, instance.getClicks());
    }

    @Test
    public void testEvents() {
        Status instance = new Status("test");
        instance.addEvent();
        instance.addEvent();
        instance.addEvent();
        assertEquals("Status[status=test,events=3,clicks=0]", instance.toString());
        assertEquals(3, instance.getEvents());
    }
}
