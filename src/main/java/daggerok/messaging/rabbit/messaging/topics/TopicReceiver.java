package daggerok.messaging.rabbit.messaging.topics;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TopicReceiver {
    private static final Logger logger = Logger.getLogger(TopicReceiver.class.getName());

    @Resource(name = "topicsLatch")
    CountDownLatch topicsLatch;

    @RabbitListener(queues = TopicsExchangeConfig.noColor)
    public void processNoColor(String data) {
        logger.info(String.format("███ %s: %s", TopicsExchangeConfig.noColor,
                data.toLowerCase().chars()
                        .mapToObj(i -> (char) i)
                        .map(ch -> String.format("%c", ch))
                        .collect(Collectors.joining("█"))));
        topicsLatch.countDown();
    }

    @RabbitListener(queues = TopicsExchangeConfig.color)
    public void processColor(String data) {
        logger.info(String.format("### %s: %s", TopicsExchangeConfig.color,
                data.toLowerCase().chars()
                        .mapToObj(i -> (char) i)
                        .map(ch -> String.format("%c", ch))
                        .collect(Collectors.joining("#"))));
        topicsLatch.countDown();
    }
}