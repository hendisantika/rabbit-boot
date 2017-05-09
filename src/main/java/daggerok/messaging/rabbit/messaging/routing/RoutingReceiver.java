package daggerok.messaging.rabbit.messaging.routing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RoutingReceiver {
    private static final Logger logger = Logger.getLogger(RoutingReceiver.class.getName());

    @Resource(name = "routingLatch")
    CountDownLatch routingLatch;

    @RabbitListener(queues = RoutingConfig.info)
    public void processInfo(String data) {
        logger.info(String.format("███ %s: %s", RoutingConfig.info,
                data.toLowerCase().chars()
                        .mapToObj(i -> (char) i)
                        .map(ch -> String.format("%c", ch))
                        .collect(Collectors.joining(""))));
        routingLatch.countDown();
    }

    @RabbitListener(queues = RoutingConfig.error)
    public void processError(String data) {
        logger.info(String.format("███ %s: %s", RoutingConfig.error,
                data.toLowerCase().chars()
                        .mapToObj(i -> (char) i)
                        .map(ch -> String.format(".%c.", ch))
                        .collect(Collectors.joining(""))));
        routingLatch.countDown();
    }
}