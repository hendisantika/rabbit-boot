package daggerok.messaging.rabbit.messaging.pubsubs;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Publisher {
    @Autowired ConnectionFactory connectionFactory;

    public void send(String message) {
        RabbitTemplate exchanged = new RabbitTemplate(connectionFactory);
        exchanged.setExchange(PublisherSubscriberConfig.publishExchange);
        exchanged.convertAndSend(message);
    }
}