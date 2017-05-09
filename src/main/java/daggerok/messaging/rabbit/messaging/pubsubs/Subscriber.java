package daggerok.messaging.rabbit.messaging.pubsubs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class Subscriber {
    private static final Logger logger = Logger.getLogger(Subscriber.class.getName());

    @Resource(name = "subscriberLatch")
    CountDownLatch subscriberLatch;

    @RabbitListener(queues = PublisherSubscriberConfig.subscriberQueueDash)
    public void processDash(String data) {
        logger.info(String.format("███ processDash: %s",
                data.toLowerCase().chars()
                        .mapToObj(i -> (char) i)
                        .map(ch -> String.format("-%c-", ch))
                        .collect(Collectors.joining(","))));
        subscriberLatch.countDown();
    }

    @RabbitListener(queues = PublisherSubscriberConfig.subscriberQueueUnderscore)
    public void processUnderscore(String data) {
        logger.info(String.format("███ processUnderscore: %s",
                data.toLowerCase().chars()
                        .mapToObj(i -> (char) i)
                        .map(ch -> String.format("_%c_", ch))
                        .collect(Collectors.joining(","))));
        subscriberLatch.countDown();
    }
}