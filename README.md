# LithientStub
A very simple jax-rs based server, using an embedded Jetty, which simply absorbs traffic for Lithient end points and throws the results on the floor.

## Usage
```
 Usage: LithientStub
 -?,--help         print this message
 -p,--port <arg>   specify the port to run on (defaults to 8080)
 -v,--version      print version
 ```

When running logs will be written under `logs` in the current working directory. These logs will automatically roll over as required, and no more than 100Mb of logs will be written. The logs include a status message emitted every minute which reports the number of SDK events and Click events absorbed since start up, and the rate per minute being handled.

## Building
Well, it's a maven project, so `mvn package` is all you need. More or less. This was built and tested against Java 8, but should work fine under Java 7. Be aware when building that one of the tests deliberately runs for over two minutes.
