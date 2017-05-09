package daggerok.messaging.rabbit.messaging.workqueues;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
public class WorkerQueueConfig {
    public static final String workerQueue = "workerQueue";

    @Bean public Queue workerQueue() {
        return new Queue(workerQueue);
    }

    @Bean public CountDownLatch workerLatch() {
        return new CountDownLatch(2);
    }
}