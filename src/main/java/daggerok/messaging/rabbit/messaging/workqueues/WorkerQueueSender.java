package daggerok.messaging.rabbit.messaging.workqueues;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerQueueSender {
    @Autowired RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(WorkerQueueConfig.workerQueue, message);
    }
}