package daggerok.messaging.rabbit.messaging.pubsubs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class PublisherSubscriberConfig {
    public static final String subscriberQueueDash = "dashQueue";

    public static final String subscriberQueueUnderscore = "underscoreQueue";

    public static final String publishExchange = "publishExchange";

    @Bean
    public Queue dashQueue() {
        return new Queue(subscriberQueueDash);
    }

    @Bean
    public Queue underscoreQueue() {
        return new Queue(subscriberQueueUnderscore);
    }

    @Bean public CountDownLatch subscriberLatch() {
        return new CountDownLatch(6);
    }

    @Bean public FanoutExchange publishExchange() {
        return new FanoutExchange(publishExchange);
    }

    @Bean
    public Binding dashToToPublishExchange() {
        return BindingBuilder.bind(dashQueue()).to(publishExchange());
    }

    @Bean
    public Binding underscoreToPublishExchange() {
        return BindingBuilder.bind(underscoreQueue()).to(publishExchange());
    }
}