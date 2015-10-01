package com.lithient.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lithient.controller.ControllerHolder;

/**
 * small service to handle any the Collector end points.
 * 
 * @author robert
 */
@Path("/ProjectXCollector")
public final class CollectorService {
    /**
     * the text message emitted with the response.
     */
    private static final String MESSAGE = "The Lithient service no longer exists. Please visit http://somoglobal.com for information\n";

    /**
     * provide ELB heartbeat end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public Response status() {
        return eventOk(false);
    }

    /**
     * handle GET to /bulk endpoint.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("bulk")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkGet() {
        return eventOk(false);
    }

    /**
     * handle POST to /bulk endpoint.
     * 
     * @param body the submitted body
     * @return a non-null response.
     */
    @POST
    @Path("bulk")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkPost(final String body) {
        return eventOk(false);
    }

    /**
     * handle GET to /bulk/v2 endpoint.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("bulk/v2")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV2Get() {
        return eventOk(false);
    }

    /**
     * handle POST to /bulk/v2 endpoint.
     * 
     * @param body the submitted body
     * @return a non-null response.
     */
    @POST
    @Path("bulk/v2")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV2Post(final String body) {
        return eventOk(false);
    }

    /**
     * handle GET to /bulk/v3 endpoint.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("bulk/v3")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV3Get() {
        return eventOk(false);
    }

    /**
     * handle POST to /bulk/v2 endpoint.
     * 
     * @param body the submitted body
     * @return a non-null response.
     */
    @POST
    @Path("bulk/v3")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV3Post(final String body) {
        return eventOk(false);
    }

    /**
     * handle POST to /bulk/v4 endpoint.
     * 
     * @param body the submitted body
     * @param appId the lithient application ID.
     * @return a non-null response.
     */
    @POST
    @Path("bulk/v4/{appId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV4Post(@PathParam("appId") final String appId, final String body) {
        return eventOk(false);
    }

    /**
     * handle GET to /bulk/v4 endpoint.
     * 
     * @param appId the lithient application ID.
     * @return a non-null response.
     */
    @GET
    @Path("bulk/v4/{appId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV4Get(@PathParam("appId") final String appId) {
        return eventFail();
    }

    /**
     * handle POST to /bulk/v4_1 endpoint.
     * 
     * @param body the submitted body
     * @param appId the lithient application ID.
     * @return a non-null response.
     */
    @POST
    @Path("bulk/v4_1/{appId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV41Post(@PathParam("appId") final String appId, final String body) {
        return eventOk(false);
    }

    /**
     * handle GET to /bulk/v4_1 endpoint.
     * 
     * @param appId the lithient application ID.
     * @return a non-null response.
     */
    @GET
    @Path("bulk/v4_1/{appId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV41Get(@PathParam("appId") final String appId) {
        return eventFail();
    }

    /**
     * handles playhaven s2s end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("s2s/playhaven/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sPlayhavenGet() {
        return clickOk();
    }

    /**
     * handles chartboost s2s end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("s2s/chartboost/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sChartboostGet() {
        return clickOk();
    }

    /**
     * handles tapcommerce s2s end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("s2s/tapcommerce/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sTapcommerceGet() {
        return clickOk();
    }

    /**
     * handles tapjoy s2s end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("s2s/tapjoy/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sTapjoyGet() {
        return clickOk();
    }

    /**
     * handles GET to "/event/v1" end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("event/v1")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sGoogleAdWordsGet() {
        return clickOk();
    }

    /**
     * handles startapp s2s end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("s2s/startapp/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sStartAppGet() {
        return clickOk();
    }

    /**
     * handles GET to "/collect/read/v2" end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("collect/read/v2")
    @Produces(MediaType.TEXT_PLAIN)
    public Response trackingPage() {
        return clickOk();
    }

    /**
     * handles GET to "/collect/write" end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("collect/write")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieReadGet() {
        return clickOk();
    }

    /**
     * handles POST to "/collect/write" end point.
     * 
     * @param body the submitted body
     * @return a non-null response.
     */
    @POST
    @Path("collect/write")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieReadPost(final String body) {
        return clickOk();
    }

    /**
     * handles GET to "/s" end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("s")
    @Produces(MediaType.TEXT_PLAIN)
    public Response clickGet() {
        return clickOk();
    }

    /**
     * handles POST to "/s" end point.
     * 
     * @param body the submitted body
     * @return a non-null response.
     */
    @POST
    @Path("s")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response clickPost(final String body) {
        return clickOk();
    }

    /**
     * handles GET to "/config/sdk/" end point.
     * 
     * @param appId the lithient application id.
     * @return a non-null response.
     */
    @GET
    @Path("config/sdk/{appId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sdkConfig(@PathParam("appId") final String appId) {
        return notFound();
    }

    /**
     * handles GET to "/config/sdk/" end point.
     * 
     * @param appId the lithient application id.
     * @param locale a standard locale name.
     * @return a non-null response.
     */
    @GET
    @Path("config/sdk/{appId}/{locale}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sdkConfig(@PathParam("appId") final String appId, @PathParam("locale") final String locale) {
        return notFound();
    }

    /**
     * handle GET to "/collect/read" end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("collect/read")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieRead() {
        return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * handle GET to "/collect/read/V1" end point.
     * 
     * @return a non-null response.
     */
    @GET
    @Path("collect/read/V1")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieReadV1() {
        return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * handle POST to "/fingerprint/sdk" end point.
     * 
     * @param body the submitted body
     * @param appId the lithient application id.
     * @return a non-null response.
     */
    @POST
    @Path("fingerprint/sdk/{appId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response fingerprint(@PathParam("appId") final String appId, final String body) {
        return Response.noContent().type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * handle POST to "/fingerprint/v2/sdk" end point.
     * 
     * @param body the submitted body
     * @param appId the lithient application id.
     * @return a non-null response.
     */
    @POST
    @Path("fingerprint/v2/sdk/{appId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response fingerprintV2(@PathParam("appId") final String appId, final String body) {
        return Response.noContent().type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * construct a 404 response.
     * 
     * @return a non-null response.
     */
    private Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * construct a 400 response.
     * 
     * @return a non-null response.
     */
    private Response eventFail() {
        ControllerHolder.getController().getStatus().addEvent();
        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * construct a 200 response for incoming SDK events.
     * 
     * @param asJson if true, body of response is a JSON object, otherwise is a plain-text message.
     * @return a non-null Response
     */
    private Response eventOk(final boolean asJson) {
        ControllerHolder.getController().getStatus().addEvent();
        if (asJson) {
            return Response.ok("{\"message\":" + MESSAGE + "\"}").type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
        }
    }

    /**
     * construct a 200 response for incoming Click events.
     * 
     * @return a non-null Response.
     */
    private Response clickOk() {
        ControllerHolder.getController().getStatus().addClick();
        return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
    }
}
