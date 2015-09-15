package com.lithient.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jcip.annotations.ThreadSafe;

/**
 * facility to contain a thread that periodically reports the current rate of processing.
 * 
 * @author robert
 */
@ThreadSafe
public final class RateReporter implements Runnable {
    /**
     * the frequency in seconds that the processing rate report fires.
     */
    public static final long REPORT_RATE = 60L;
    /**
     * class level logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(RateReporter.class);
    /**
     * scheduler used to kick off periodic report of processing rate.
     */
    private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor();
    /**
     * the handler for the processing rate report.
     */
    private static volatile ScheduledFuture<?> rateHandle = null;
    /**
     * the injected Controller that is the source of counts.
     */
    private final Controller controller;
    /**
     * the last reported count of clicks.
     */
    private long lastClicks = 0L;
    /**
     * the last reported count of events.
     */
    private long lastEvents = 0L;

    /**
     * primary constructor.
     * 
     * @param holder injected object which is the source of report data.
     */
    private RateReporter(final Controller holder) {
        controller = holder;
    }

    @Override
    public void run() {
        long currentClicks = controller.getStatus().getClicks();
        final long clickDelta = (currentClicks - lastClicks) * (DateTimeConstants.SECONDS_PER_MINUTE / REPORT_RATE);
        lastClicks = currentClicks;

        long currentEvents = controller.getStatus().getEvents();
        final long eventDelta = (currentEvents - lastEvents) * (DateTimeConstants.SECONDS_PER_MINUTE / REPORT_RATE);
        lastEvents = currentEvents;

        LOG.info("[report_type=\"collector_stats\" click_count=\"{}\" click_rate=\"{}\" event_count=\"{}\" event_rate=\"{}\"]",
                currentClicks, clickDelta, currentEvents, eventDelta);
    }

    /**
     * attempt to start the report scheduler.
     * 
     * @param holder injected object which is the source of report data.
     */
    public static synchronized void start(final Controller holder) {
        if (rateHandle != null) {
            rateHandle.cancel(true);
        }
        rateHandle = SCHEDULER.scheduleAtFixedRate(new RateReporter(holder), RateReporter.REPORT_RATE, RateReporter.REPORT_RATE,
                TimeUnit.SECONDS);
    }

    /**
     * attempt to halt the report scheduler.
     */
    public static synchronized void stop() {
        if (rateHandle != null) {
            rateHandle.cancel(true);
        }
    }
}
