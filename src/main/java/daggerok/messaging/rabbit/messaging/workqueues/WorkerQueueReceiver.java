package daggerok.messaging.rabbit.messaging.workqueues;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

@Component
public class WorkerQueueReceiver {
    private static final Logger logger = Logger.getLogger(WorkerQueueReceiver.class.getName());

    @Resource(name = "workerLatch")
    CountDownLatch workerLatch;

    @RabbitListener(
            bindings = @QueueBinding(
            value = @Queue(value = WorkerQueueConfig.workerQueue, durable = "true"),
            exchange = @Exchange(value = WorkerQueueConfig.workerQueue),
            key = WorkerQueueConfig.workerQueue)
    )
    public void processWorker1(String data) {
        logger.info(String.format("███ processWorker1: %s", data.toLowerCase()));
        workerLatch.countDown();
    }

    @RabbitListener(queues = WorkerQueueConfig.workerQueue)
    public void processWorker2(String data) {
        logger.info(String.format("███ processWorker2: %s", data.toUpperCase()));
        workerLatch.countDown();
    }
}