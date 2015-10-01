package com.lithient;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import com.lithient.api.LithientStubApp;

/**
 * this is the wrapper on the Jetty server. This is broken out of the main app so that we can use the
 * app to run a server and as a test harness.
 * 
 * @author robert
 */
public final class JettyServer {
    /**
     * number of days to retain access logs.
     */
    private static final int LOG_RETAIN_DAYS = 14;
    /**
     * default port.
     */
    public static final int PORT = 8080;
    /**
     * the port we will run on.
     */
    private final int port;

    /**
     * default constructor.
     */
    public JettyServer() {
        port = PORT;
    }

    /**
     * construct to use a specified port.
     * 
     * @param value the port to specify, assumed but not required to be a useful number.
     */
    public JettyServer(final int value) {
        port = value;
    }

    /**
     * start an embedded Jetty. This could use considerable more work - it would be very nice to ensure that we cleanly shut down.
     * 
     * @throws Exception if starting up or stopping the server fails.
     */
    public void start() throws Exception {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(port).build();
        Server jettyServer = JettyHttpContainerFactory.createServer(baseUri, new LithientStubApp(), false);
        jettyServer.setStopAtShutdown(true);

        addAccessLogging(jettyServer);

        jettyServer.start();
        jettyServer.join();
    }

    /**
     * enable an NCSA request log for the provided server instance.
     * 
     * @param server the server to enable logging on.
     */
    private void addAccessLogging(final Server server) {
        HandlerCollection handlers = new HandlerCollection(true);
        // need to preserve the existing set of handlers, otherwise nothing goes through to the jersey layer.
        for (Handler handler : server.getHandlers()) {
            handlers.addHandler(handler);
        }
        server.setHandler(handlers);

        NCSARequestLog requestLog = new NCSARequestLog();
        requestLog.setFilename("logs/yyyy_mm_dd.request.log");
        requestLog.setFilenameDateFormat("yyyy_MM_dd");
        requestLog.setRetainDays(LOG_RETAIN_DAYS);
        requestLog.setAppend(true);
        requestLog.setExtended(true);
        requestLog.setLogCookies(false);
        requestLog.setLogTimeZone("UTC");

        RequestLogHandler requestLogHandler = new RequestLogHandler();
        requestLogHandler.setRequestLog(requestLog);
        handlers.addHandler(requestLogHandler);

    }
}
