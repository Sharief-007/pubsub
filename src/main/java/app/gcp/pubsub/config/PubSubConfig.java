package app.gcp.pubsub.config;

import com.google.cloud.spring.pubsub.core.PubSubOperations;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@Slf4j
public class PubSubConfig {
    @Bean
    public MessageChannel getInputChannel(){
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter getInboundAdapter(
            @Qualifier("getInputChannel") MessageChannel channel,
            PubSubOperations template){
        var adapter = new PubSubInboundChannelAdapter(template, "hello_subscription");
        adapter.setOutputChannel(channel);
        adapter.setAckMode(AckMode.AUTO);
        return adapter;
    }

    @ServiceActivator(inputChannel = "getInputChannel")
    @Bean
    public MessageHandler getMessageHandler(){
        return message -> {
            log.info(message.getPayload().toString());
        };
    }
}
