package com.lithient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.lithient.controller.Controller;
import com.lithient.controller.ControllerHolder;
import com.lithient.controller.ControllerImpl;

/**
 * main executable class. Note that there are no unit tests against this class, as it's just the runtime framework.
 */
public final class LithientStub {
    /**
     * application properties loaded from the classpath.
     */
    private static final Properties PROPERTIES = loadProperties();

    /**
     * main entry point.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("?", "help", false, "print this message");
        options.addOption("v", "version", false, "print version");
        options.addOption("p", "port", true, "specify the port to run on (defaults to 8080)");
        options.addOption("t", "test", true, "execute tests against a given url");
        options.addOption("x", "debug", false, "turn on debug mode for test");

        CommandLineParser parser = new PosixParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption('?')) {
                doHelp(options);
            } else if (cmd.hasOption('v')) {
                doVersion();
            } else if (cmd.hasOption('t')) {
                doTest(StringUtils.strip(cmd.getOptionValue('t')), cmd.hasOption('x'));
            } else {
                int port;
                if (cmd.hasOption('p')) {
                    port = NumberUtils.toInt(StringUtils.strip(cmd.getOptionValue('p')), 8080);
                } else {
                    port = 8080;
                }
                executeServer(port);
            }
        } catch (ParseException ex) {
            doHelp(options);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * run tests.
     * 
     * @param url the target URL, which is assumed to be a useful and meaningful URL.
     * @param debug if true, test will do a trace of requests and responses.
     */
    private static void doTest(final String url, final boolean debug) {
        Tester instance = new Tester(url, debug);
        instance.execute();
    }

    /**
     * run the server instance. Note that this is a blocking call - we disappear into the jetty server until
     * the JVM is halted.
     * 
     * @throws IOException if we cannot read resources
     */
    private static void executeServer(final int port) throws IOException {
        Controller controller = new ControllerImpl();
        ControllerHolder.setController(controller);

        JettyServer instance = new JettyServer(port);
        try {
            ControllerHolder.getController().activate();
            instance.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * report the application version to standard out.
     */
    private static void doVersion() {
        String name = PROPERTIES.getProperty("project.name");
        String version = PROPERTIES.getProperty("project.version");
        System.out.println(String.format("%s [%s]", name, version));
    }

    /**
     * print command line options to standard out.
     * 
     * @param options the command line options.
     */
    private static void doHelp(final Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(PROPERTIES.getProperty("project.name"), options);
    }

    /**
     * load properties from the class path.
     * 
     * @return a Properties object, which should be non-null unless theres an exception.
     */
    private static Properties loadProperties() {
        Properties props = new Properties();
        try {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("project.properties");
            props.load(in);
        } catch (IOException ioe) {
            System.err.println("Failed to read application properties");
        }
        return props;
    }
}
