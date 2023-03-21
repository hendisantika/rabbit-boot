package com.hendisantika.messaging.rabbit.rest;

import com.hendisantika.messaging.rabbit.messaging.prc.RpcSenderReceiver;
import com.hendisantika.messaging.rabbit.messaging.pubsubs.Publisher;
import com.hendisantika.messaging.rabbit.messaging.routing.RoutingSender;
import com.hendisantika.messaging.rabbit.messaging.simple.SimpleSender;
import com.hendisantika.messaging.rabbit.messaging.topics.TopicSender;
import com.hendisantika.messaging.rabbit.messaging.workqueues.WorkerQueueSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.isNull;

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

    @GetMapping("")
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

    @GetMapping("/to/{dst}")
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
