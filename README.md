rabbit-boot [![build](https://travis-ci.org/daggerok/rabbit-boot.svg?branch=master)](https://travis-ci.org/daggerok/rabbit-boot)
===========

boot rabbit messaging!

**tags**

- rabbit:
  - simple send, receive
  - workers (work queues)
  - publish, subscriber
  - routing (direct exchange)
  - topics (topic exchange)
  - rpc (remote procedure calls)
- spring:
  - spring messaging
  - spring boot
  - mvc
  - error handling
  - mustache template engine
- gradle + webjars

latest:
```bash
rm -rf etc/ var/
docker-compose up
gradle run
```

old:

```shell
$ wget -qO-  http://www.rabbitmq.com/releases/rabbitmq-server/v3.6.0/rabbitmq-server-generic-unix-3.6.0.tar.xz | tar -xvf-
$ rabbitmq_server-3.6.0/sbin/rabbitmq-server
$ git clone https://github.com/daggerok/rabbit-boot
$ gradle run
```

1. go to http://localhost:8080 
2. send message
3. verify logs

or

1. import project into idea
2. right click -> Run -> All Tests
