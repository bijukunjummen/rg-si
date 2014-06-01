package rube.complicated;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface EchoGateway {
	@Gateway(requestChannel = "requestChannel")
	String echo(String message);

}
