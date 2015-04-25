package jsfdemo.services.camel;

import org.apache.camel.ProducerTemplate;

/**
 * A Camel client to send messages to a TCP endpoint.
 * 
 * @author zedeng
 *
 */
public class MyTcpClientMain extends CamelService {

	public static void main(String[] args) throws Exception {
		MyTcpClientMain service = new MyTcpClientMain();
		service.start();
		service.stop();
	}
	
	@Override
	protected void afterCamelStarted() {
		ProducerTemplate producerTemplate = getCamelContext().createProducerTemplate();
		producerTemplate.asyncSendBody("netty:tcp://localhost:12345", "Hello World!");
	}
}
