package daggerok.messaging.rabbit.messaging.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleSender {
    @Autowired RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(SimpleSendReceiveConfig.simpleQueue, message);
    }
}