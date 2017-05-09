package daggerok.messaging.rabbit;

import daggerok.messaging.rabbit.config.MessagingRabbitApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MessagingRabbitApplicationConfig.class)
public class MessagingRabbitApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessagingRabbitApplication.class, args)
                .registerShutdownHook();
    }
}
