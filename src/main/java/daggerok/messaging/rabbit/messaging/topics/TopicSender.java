package daggerok.messaging.rabbit.messaging.topics;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TopicSender {
    @Autowired ConnectionFactory connectionFactory;

    public void send(String message) {
        if (Arrays.asList("white","black","red","green","blue","pink","yellow").contains(message)) {
            if (message.toLowerCase().contains("black") || message.toLowerCase().contains("white")) {
                noColor(message);
            } else {
                color(message);
            }
        }
    }

    public void send(String routingKey, String message) {
        template(routingKey, message);
    }

    public void noColor(String message) {
        template(TopicsExchangeConfig.noColor, message);
    }

    public void color(String message) {
        template(TopicsExchangeConfig.color, message);
    }

    private void template(String routing, String message) {
        RabbitTemplate exchanged = new RabbitTemplate(connectionFactory);
        exchanged.setExchange(TopicsExchangeConfig.topicsExchange);
        exchanged.convertAndSend(routing, message);
    }
}