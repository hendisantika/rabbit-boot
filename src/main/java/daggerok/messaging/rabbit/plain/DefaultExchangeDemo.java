package daggerok.messaging.rabbit.plain;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static java.util.Objects.nonNull;

@Slf4j
public class DefaultExchangeDemo {

    public static void main(String[] args) {
        defaultExchangeDemo();
    }

    private static void defaultExchangeDemo() {

        log.info("{} sending message.", System.currentTimeMillis());

        val sender = new Sender();

        sender.connect();
        sender.send("test message");
        sender.destroy();

        log.info("{} message sent.", System.currentTimeMillis());
    }
}

class Sender {

    ConnectionFactory connectionFactory;
    Connection connection;
    Channel channel;
    AMQP.Queue.DeclareOk declareOk;
    AMQP.BasicProperties props;

    @SneakyThrows
    public void connect() {

        connectionFactory = new ConnectionFactory();

        val creds = "guest";
        // see .docker.secrets
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername(creds);
        connectionFactory.setPassword(creds);

        // tcp connection
        connection = connectionFactory.newConnection();

        // virtual channel to interruct with mq (for create queues / exchanges, send msgs or subscribe to broker)
        channel = connection.createChannel();
    }

    @SneakyThrows
    public void send(final String message) {

        // if queue created it will not be re-created again:
        declareOk = channel.queueDeclare("queue-name", false, false, false, null);

        // send amqp publish
        channel.basicPublish("", "queue-name", null, message.getBytes());
    }

    @SneakyThrows
    public void send(final String message, final String exchange, final String type) {

        channel.exchangeDeclare(exchange, type);
        channel.basicPublish(exchange, "", null, message.getBytes());
    }

    @SneakyThrows
    public void send(final String message, final String correlationId) {

        channel.queueDeclare("request-queue", false, false, false, null);
        channel.queueDeclare("response-queue", false, false, false, null);

        props = new AMQP.BasicProperties()
                .builder()
                .correlationId(correlationId)
                .replyTo("response-queue")
                .build();

        channel.basicPublish("default-exchange", "request-queue", props, message.getBytes());
    }

    @SneakyThrows
    public void destroy() {

        if (nonNull(connection)) {
            connection.close();
        }
    }
}
