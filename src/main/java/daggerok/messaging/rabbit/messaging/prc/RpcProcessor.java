package daggerok.messaging.rabbit.messaging.prc;

import daggerok.messaging.rabbit.messaging.topics.TopicReceiver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RpcProcessor {
    private static final Logger logger = Logger.getLogger(TopicReceiver.class.getName());

    @RabbitListener(queues = RemoteProcedureCallsConfig.prcQueue)
    public String process(String data) {
        String result = String.format("███ %s: %s", RemoteProcedureCallsConfig.prcQueue
                , data.toLowerCase().chars()
                .mapToObj(i -> (char) i)
                .map(ch -> String.format("%c", ch).toUpperCase())
                .collect(Collectors.joining("█")));
        logger.info(result);
        return result;
    }
}