package com.lithient;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * simple bean to hold a status for the service.
 * 
 * @author robert
 */
public final class Status {
    /**
     * the status of the service.
     */
    @JsonProperty("status")
    private String status;

    /**
     * count of clicks handled.
     */
    @JsonIgnore
    private final AtomicLong clicks = new AtomicLong(0);

    /**
     * count of SDK events handled.
     */
    @JsonIgnore
    private final AtomicLong events = new AtomicLong(0);

    /**
     * primary constructor.
     * 
     * @param value
     */
    public Status(final String value) {
        setStatus(value);
    }

    /**
     * constructor for json deserialisation.
     * 
     * @param aStatus the status to store.
     * @param count the reported count.
     */
    @JsonCreator
    public Status(@JsonProperty("status") String aStatus, @JsonProperty("clicks") long clickCount,
            @JsonProperty("events") long eventCount) {
        status = aStatus;
        clicks.set(clickCount);
        events.set(eventCount);
    }

    /**
     * retrieve the count of clicks handled.
     * 
     * @return the count of clicks handled.
     */
    @JsonProperty("clicks")
    public long getClicks() {
        return clicks.get();
    }

    /**
     * increment the click count.
     */
    public void addClick() {
        clicks.incrementAndGet();
    }

    /**
     * retrieve the count of events handled.
     * 
     * @return the count of events handled.
     */
    @JsonProperty("events")
    public long getEvents() {
        return events.get();
    }

    /**
     * increment the event count.
     */
    public void addEvent() {
        events.incrementAndGet();
    }

    /**
     * retrieve the current status string.
     * 
     * @return the current status string.
     */
    public String getStatus() {
        return status;
    }

    /**
     * mutator - update the status.
     * 
     * @param value the status to record.
     */
    public void setStatus(final String value) {
        status = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("status", status).append("events", getEvents())
                .append("clicks", getClicks()).toString();
    }
}
