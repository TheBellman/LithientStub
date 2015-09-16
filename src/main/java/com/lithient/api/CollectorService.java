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
    private static final String MESSAGE = "The Lithient service no longer exists. Please visit http://somoglobal.com for more information\n";

    @GET
    @Path("bulk")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkGet() {
        return eventOk(false);
    }

    @POST
    @Path("bulk")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkPost() {
        return eventOk(false);
    }

    @GET
    @Path("bulk/v2")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV2Get() {
        return eventOk(false);
    }

    @POST
    @Path("bulk/v2")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV2Post() {
        return eventOk(false);
    }

    @GET
    @Path("bulk/v3")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV3Get() {
        return eventOk(false);
    }

    @POST
    @Path("bulk/v3")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV3Post() {
        return eventOk(false);
    }

    @POST
    @Path("bulk/v4/{appId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV4Post(@PathParam("appId") final String appId) {
        return eventOk(false);
    }

    @GET
    @Path("bulk/v4/{appId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV4Get(@PathParam("appId") final String appId) {
        return eventFail();
    }

    @POST
    @Path("bulk/v4_1/{appId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_OCTET_STREAM})
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV41Post(@PathParam("appId") final String appId) {
        return eventOk(false);
    }

    @GET
    @Path("bulk/v4_1/{appId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bulkV41Get(@PathParam("appId") final String appId) {
        return eventFail();
    }

    @GET
    @Path("s2s/playhaven/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sPlayhavenGet() {
        return clickOk();
    }

    @GET
    @Path("s2s/chartboost/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sChartboostGet() {
        return clickOk();
    }

    @GET
    @Path("s2s/tapcommerce/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sTapcommerceGet() {
        return clickOk();
    }

    @GET
    @Path("s2s/tapjoy/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sTapjoyGet() {
        return clickOk();
    }

    @GET
    @Path("event/v1")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sGoogleAdWordsGet() {
        return clickOk();
    }

    @GET
    @Path("s2s/startapp/click")
    @Produces(MediaType.TEXT_PLAIN)
    public Response s2sStartAppGet() {
        return clickOk();
    }

    @GET
    @Path("collect/read/v2")
    @Produces(MediaType.TEXT_PLAIN)
    public Response trackingPage() {
        return clickOk();
    }

    @GET
    @Path("collect/write")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieReadGet() {
        return clickOk();
    }

    @POST
    @Path("collect/write")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieReadPost() {
        return clickOk();
    }

    @GET
    @Path("s")
    @Produces(MediaType.TEXT_PLAIN)
    public Response clickGet() {
        return clickOk();
    }

    @POST
    @Path("s")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response clickPost() {
        return clickOk();
    }

    @GET
    @Path("config/sdk/{appId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sdkConfig(@PathParam("appId") final String appId) {
        return notFound();
    }

    @GET
    @Path("config/sdk/{appId}/{locale}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sdkConfig(@PathParam("appId") final String appId, @PathParam("locale") final String locale) {
        return notFound();
    }

    @GET
    @Path("collect/read")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieRead() {
        return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("collect/read/V1")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cookieReadV1() {
        return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("fingerprint/sdk/{appId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response fingerprint(@PathParam("appId") final String appId) {
        return Response.noContent().type(MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("fingerprint/v2/sdk/{appId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response fingerprintV2(@PathParam("appId") final String appId) {
        return Response.noContent().type(MediaType.TEXT_PLAIN).build();
    }

    private Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).build();
    }

    private Response eventFail() {
        ControllerHolder.getController().getStatus().addEvent();
        return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN).build();
    }

    private Response eventOk(final boolean asJson) {
        ControllerHolder.getController().getStatus().addEvent();
        if (asJson) {
            return Response.ok("{\"message\":" + MESSAGE + "\"}").type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
        }
    }

    private Response clickOk() {
        ControllerHolder.getController().getStatus().addClick();
        return Response.ok(MESSAGE).type(MediaType.TEXT_PLAIN).build();
    }
}
