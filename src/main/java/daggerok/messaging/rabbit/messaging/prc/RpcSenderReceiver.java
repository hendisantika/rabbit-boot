package daggerok.messaging.rabbit.messaging.prc;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RpcSenderReceiver {
    @Autowired ConnectionFactory connectionFactory;

    public String send(String message) {
        RabbitTemplate queued = new RabbitTemplate(connectionFactory);
        queued.setQueue(RemoteProcedureCallsConfig.prcQueue);
        queued.setReplyTimeout(60_000L);
        queued.convertAndSend(message);

        String response = (String) queued.convertSendAndReceive(RemoteProcedureCallsConfig.prcQueue, message);

        return String.format("procedure response: %s", response);
    }
}