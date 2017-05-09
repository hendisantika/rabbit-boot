package daggerok.messaging.rabbit.messaging.topics;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class TopicsExchangeConfig {
    public static final String noColor = "noColorQueue";

    public static final String color = "colorQueue";

    public static final String topicsExchange = "topicsExchange";

    @Bean
    public Queue noColorQueue() {
        return new Queue(noColor);
    }

    @Bean
    public Queue colorQueue() {
        return new Queue(color);
    }

    @Bean public CountDownLatch topicsLatch() {
        return new CountDownLatch(8);
    }

    @Bean public TopicExchange topicsExchange() {
        return new TopicExchange(topicsExchange);
    }

    @Bean
    public Binding noColorWithNoColor(TopicExchange exchange5) {
        return BindingBuilder.bind(noColorQueue()).to(exchange5).with(noColor);
    }

    @Bean
    public Binding colorWithNoColor(TopicExchange exchange5) {
        return BindingBuilder.bind(colorQueue()).to(exchange5).with(noColor);
    }

    @Bean
    public Binding colorWithColor(TopicExchange exchange5) {
        return BindingBuilder.bind(colorQueue()).to(exchange5).with(color);
    }
}