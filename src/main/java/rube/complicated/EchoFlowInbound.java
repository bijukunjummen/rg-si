package rube.complicated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.ChannelPublishingJmsMessageListener;
import org.springframework.integration.jms.JmsMessageDrivenEndpoint;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

@Configuration
@ImportResource("classpath:/rube/complicated/broker.xml")
public class EchoFlowInbound {

	@Bean
	public JmsMessageDrivenEndpoint jmsInbound() {
		JmsMessageDrivenEndpoint jmsInbound = new JmsMessageDrivenEndpoint(listenerContainer(), messageListener());
		return jmsInbound;
	}

	@Bean
	public IntegrationFlow inboundFlow() {
		return IntegrationFlows.from(enhanceMessageChannel())
				.<String, String>transform(s -> s.toUpperCase())
				.get();
	}

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	public DirectChannel enhanceMessageChannel() {
		return new DirectChannel();
	}

	@Bean
	public DefaultMessageListenerContainer listenerContainer() {
		DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
		listenerContainer.setDestinationName("amq.outbound");
		listenerContainer.setConnectionFactory(connectionFactory);
		return listenerContainer;
	}

	@Bean
	public ChannelPublishingJmsMessageListener messageListener() {
		ChannelPublishingJmsMessageListener messageListener = new ChannelPublishingJmsMessageListener();
		messageListener.setRequestChannel(enhanceMessageChannel());
		messageListener.setExpectReply(true);
		return messageListener;
	}
}
