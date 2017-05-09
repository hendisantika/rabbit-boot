package daggerok.messaging.rabbit.messaging.prc;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteProcedureCallsConfig {
    public static final String prcQueue = "rpcQueue";

    @Bean
    public Queue rpcQueue() {
        return new Queue(prcQueue);
    }
}