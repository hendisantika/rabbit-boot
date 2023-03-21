package com.hendisantika.messaging.rabbit.config;

import com.hendisantika.messaging.rabbit.config.rabbit.RabbitConfig;
import com.hendisantika.messaging.rabbit.config.web.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        WebConfig.class,
        RabbitConfig.class, })
public class MessagingRabbitApplicationConfig {}
