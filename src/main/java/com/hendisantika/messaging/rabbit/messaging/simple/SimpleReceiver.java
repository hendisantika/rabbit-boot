package com.hendisantika.messaging.rabbit.messaging.simple;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

@Service
public class SimpleReceiver {
    private static final Logger logger = Logger.getLogger(SimpleReceiver.class.getName());

    @Resource(name = "simpleLatch")
    CountDownLatch simpleLatch;

    @RabbitListener(queues = SimpleSendReceiveConfig.simpleQueue)
    public void process(String message) {
        logger.info("███ process: " + message);
        simpleLatch.countDown();
    }
}
