package outbound;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("httpgateway.xml")
public class TestHttpOutboundGateway {
	
	@Autowired @Qualifier("quakeinfo.channel") PollableChannel quakeinfoChannel;

	@Test
	public void testHttpOutbound() {
		Message<?> message = quakeinfoChannel.receive();
		assertThat(message.getPayload(), is(notNullValue()));
	}

}
