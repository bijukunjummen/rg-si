package rube.complicated;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EchoFlowJavaDslTest {
	
	@Autowired EchoGateway echoGateway;

	@Test
	@DirtiesContext
	public void testEcho() throws Exception{
		String amessage = "Hello from Spring Integration";
		
		String response = echoGateway.echo(amessage);
		assertThat(response, is("HELLO FROM SPRING INTEGRATION"));
	}

	@Import({EchoFlow.class})
	@Configuration
	public static class ContextConfig {

	}
}