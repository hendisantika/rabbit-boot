package daggerok.messaging.rabbit.config;

import daggerok.messaging.rabbit.config.rabbit.RabbitConfig;
import daggerok.messaging.rabbit.config.web.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        WebConfig.class,
        RabbitConfig.class, })
public class MessagingRabbitApplicationConfig {}
