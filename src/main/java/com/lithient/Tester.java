package com.lithient;

import java.text.NumberFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.Validate;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.joda.time.DateTimeConstants;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * simple volume test against a given url that should have a running instance of the stub service.
 * 
 * @author robert
 */
public final class Tester {
    /**
     * the number of iterations to run repeated tests over.
     */
    private final static int LOOPS = 1000;
    /**
     * the Jersey client being used.
     */
    private final transient Client client;
    /**
     * the base reference for all end points.
     */
    private final transient WebTarget baseTarget;

    /**
     * primary constructor.
     * 
     * @param url the URL to target. It is assumed that this is a valid URL.
     * @param debug if true will dump a detailed request/response trace.
     */
    public Tester(final String url, final boolean debug) {
        final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jacksonJsonProvider.setMapper(new JodaMapper());

        ClientConfig cc = new ClientConfig(jacksonJsonProvider).register(JacksonFeature.class)
                .property(ClientProperties.CONNECT_TIMEOUT, Integer.toString(5000))
                .property(ClientProperties.READ_TIMEOUT, Integer.toString(5000));

        client = ClientBuilder.newClient(cc);
        if (debug) {
            client.register(new LoggingFilter());
        }
        baseTarget = client.target(url);
    }

    /**
     * run a series of tests.
     */
    public void execute() {
        checkStatus();
        sendClicks();
        sendEvents();
        checkStatus();
    }

    /**
     * hit the /s end point repeatedly.
     */
    private void sendClicks() {
        System.out.println("Start testing sendClicks()");
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOPS; i++) {
            String result = fetchObjectFromTarget(String.class, baseTarget.path("/ProjectXCollector/s"), MediaType.TEXT_PLAIN);
            Validate.notNull(result);
        }
        long delta = System.currentTimeMillis() - startTime;
        System.out.println(String.format("Elapsed = %s ms, clicks per minute = %d", NumberFormat.getInstance().format(delta),
                DateTimeConstants.MILLIS_PER_MINUTE / (delta / LOOPS)));
    }

    /**
     * hit one of the bulk end points repeatedly.
     */
    private void sendEvents() {
        System.out.println("Start testing sendEvents()");
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOPS; i++) {

            Response response = baseTarget.path("/ProjectXCollector/bulk/v4").path(Integer.toString(i)).request(MediaType.TEXT_PLAIN)
                    .post(Entity.entity(Integer.toString(i), MediaType.APPLICATION_OCTET_STREAM));
            String result = parseResponse(response, String.class);
            Validate.notNull(result);
        }
        long delta = System.currentTimeMillis() - startTime;
        System.out.println(String.format("Elapsed = %s ms, clicks per minute = %d", NumberFormat.getInstance().format(delta),
                DateTimeConstants.MILLIS_PER_MINUTE / (delta / LOOPS)));

    }

    /**
     * call the status end point and verify we get a valid status back.
     */
    private void checkStatus() {
        Status status = fetchObjectFromTarget(Status.class, baseTarget.path("/status"), MediaType.APPLICATION_JSON);
        Validate.notNull(status);
        System.out.println("checkStatus returned: " + status.toString());
    }

    /**
     * post an object to a web target, and deal with the result.
     * 
     * @param returnType the type of data returned from the call.
     * @param target the target of the call.
     * @param payLoad the object to serialise and post.
     * @param T the type of object we are returning.
     * @return the response object if it can be found and deserialised, or null otherwise
     */
    // private <T> T postObjectToTarget(final String testMethod, final Class<T> returnType, final WebTarget target, final Object payLoad) {
    // try {
    //
    // Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(payLoad));
    // return parseResponse(testMethod, response, returnType);
    // } catch (Exception ex) {
    // System.out.println(testMethod + "() serious failure: " + ex.getMessage());
    // return null;
    // }
    // }

    /**
     * fetch an object from the web target by making the call and deserialising the result.
     * 
     * @param returnType the type of object the call should return.
     * @param target the target of the call.
     * @param contentType the content-type of the request.
     * @param T the type of object we are returning.
     * @return the object, if it can be found and deserialised, or null otherwise.
     */
    private <T> T fetchObjectFromTarget(final Class<T> returnType, final WebTarget target, final String contentType) {
        try {
            Response response = target.request(contentType).get();
            return parseResponse(response, returnType);
        } catch (Exception ex) {
            System.out.println("serious failure: " + ex.getMessage());
            return null;
        }
    }

    /**
     * parse a response to extract an object, if possible.
     * 
     * @param response the response being parsed
     * @param returnType the type of object the call should return.
     * @return the object it if can be deserialised from the response, null otherwise.
     */
    private <T> T parseResponse(final Response response, final Class<T> returnType) {
        if (response.getStatus() == 200) {
            T object = response.readEntity(returnType);
            return object;
        } else {
            System.out.println("FAILED - HTTP response = " + response.getStatus());
            return null;
        }

    }
}
