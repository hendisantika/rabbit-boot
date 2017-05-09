package daggerok.messaging.rabbit.messaging.routing;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class RoutingConfig {
    public static final String info = "info";

    public static final String error = "error";

    public static final String routingExchange = "routingExchange";

    @Bean
    public Queue info() {
        return new Queue(info);
    }

    @Bean
    public Queue error() {
        return new Queue(error);
    }

    @Bean public CountDownLatch routingLatch() {
        return new CountDownLatch(3);
    }

    @Bean public DirectExchange routingExchange() {
        return new DirectExchange(routingExchange);
    }

    @Bean
    public Binding infoWithInfo(DirectExchange routingExchange) {
        return BindingBuilder.bind(info()).to(routingExchange).with(info);
    }

    @Bean
    public Binding infoWithError(DirectExchange routingExchange) {
        return BindingBuilder.bind(info()).to(routingExchange).with(error);
    }

    @Bean
    public Binding errorWithError(DirectExchange routingExchange) {
        return BindingBuilder.bind(error()).to(routingExchange).with(error);
    }
}