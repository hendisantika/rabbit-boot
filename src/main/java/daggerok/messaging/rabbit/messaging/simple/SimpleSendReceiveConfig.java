package daggerok.messaging.rabbit.messaging.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class SimpleSendReceiveConfig {
    public static final String simpleQueue = "simpleQueue";

    @Bean Queue simpleQueue() {
        return new Queue(simpleQueue, false);
    }


    @Bean public CountDownLatch simpleLatch() {
        return new CountDownLatch(1);
    }
}
