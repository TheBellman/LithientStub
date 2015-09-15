package com.lithient.controller;

import static org.mockito.Mockito.verify;

import org.joda.time.DateTimeConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RateReporterTest {
    @Mock
    private Controller controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartStop() throws Exception {
        RateReporter.start(controller);
        RateReporter.stop();
    }

    @Test
    public void testStartWaitStop() throws Exception {
        RateReporter.start(controller);

        // Note that the +1 on the timer stopped it failing intermittently
        Thread.sleep(DateTimeConstants.MILLIS_PER_SECOND * RateReporter.REPORT_RATE * 2 + 1);

        verify(controller).getStatus();
        RateReporter.stop();
    }
}
