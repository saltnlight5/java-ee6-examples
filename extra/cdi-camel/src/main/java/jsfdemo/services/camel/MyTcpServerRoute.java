package jsfdemo.services.camel;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Camel route that listen on a TCP port for incoming messages.
 * 
 * @author zedeng
 *
 */
public class MyTcpServerRoute extends RouteBuilder {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTcpServerRoute.class);
	
	@Override
	public void configure() throws Exception {
		LOGGER.info("I will be listening on 'localhost' port 12345");
		from("netty:tcp://localhost:12345")
			.to("log://" + CamelService.class.getSimpleName());
	}

}
