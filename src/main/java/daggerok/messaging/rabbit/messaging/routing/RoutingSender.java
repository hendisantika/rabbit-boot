package daggerok.messaging.rabbit.messaging.routing;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutingSender {
    @Autowired ConnectionFactory connectionFactory;

    public void send(String message) {
        if (message.toLowerCase().contains("log")) {
            if (message.toLowerCase().contains("error")) {
                error(message);
            } else {
                info(message);
            }
        }
    }

    public void send(String routingKey, String message) {
        template(routingKey, message);
    }

    public void info(String message) {
        template(RoutingConfig.info, message);
    }

    public void error(String message) {
        template(RoutingConfig.error, message);
    }

    private void template(String routing, String message) {
        RabbitTemplate exchanged = new RabbitTemplate(connectionFactory);
        exchanged.setExchange(RoutingConfig.routingExchange);
        exchanged.convertAndSend(routing, message);
    }
}