package daggerok.messaging.rabbit.rest;

import daggerok.messaging.rabbit.messaging.prc.RpcSenderReceiver;
import daggerok.messaging.rabbit.messaging.pubsubs.Publisher;
import daggerok.messaging.rabbit.messaging.routing.RoutingSender;
import daggerok.messaging.rabbit.messaging.simple.SimpleSender;
import daggerok.messaging.rabbit.messaging.topics.TopicSender;
import daggerok.messaging.rabbit.messaging.workqueues.WorkerQueueSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/message/send")
public class MessagingService {

    final Publisher publisher;
    final TopicSender topicSender;
    final SimpleSender simpleSender;
    final RoutingSender routingSender;
    final WorkerQueueSender workerQueueSender;
    final RpcSenderReceiver rpcSenderReceiver;

    @RequestMapping("")
    public ResponseEntity<String> send(final String message) {

        log.info("processing new message: {}", message);

        if (null != message && !"".equals(message)) {
            simpleSender.send(message);
            workerQueueSender.send(message);
            publisher.send(message);
            routingSender.send(message);
            topicSender.send(message);
            rpcSenderReceiver.send(message);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/to/{dst}")
    public ResponseEntity<String> sendTo(final @PathVariable Optional<String> dst, @RequestParam final String message) {

        log.info("processing message {} to: {}", message, dst);

        if (isNull(message) || "".equals(message)) {
            return ResponseEntity
                    .badRequest()
                    .body("message is required");
        }

        Optional.ofNullable(dst).ifPresent(d -> {

            switch (d.get()) {

                case "simpleSender":
                    simpleSender.send(message);
                    break;

                case "workerQueueSender":
                    workerQueueSender.send(message);
                    break;

                case "publisher":
                    publisher.send(message);
                    break;

                case "routingSender":
                    routingSender.send(message);
                    break;

                case "topicSender":
                    topicSender.send(message);
                    break;

                case "rpcSenderReceiver":
                    rpcSenderReceiver.send(message);
                    break;

                default:
                    log.info("no receiver found for {}", d.get());
                    send(message);
            }
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
