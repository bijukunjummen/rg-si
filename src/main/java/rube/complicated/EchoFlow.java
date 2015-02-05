package rube.complicated;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@ComponentScan
@Import({EchoFlowOutBound.class, EchoFlowInbound.class})
public class EchoFlow {

    public static void main(String[] args) {
        SpringApplication.run(EchoFlow.class, args);
    }

}
