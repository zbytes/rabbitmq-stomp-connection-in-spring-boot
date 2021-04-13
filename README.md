<h1 align="center">
  @zbytes/rabbitmq-stomp-connection-in-spring-boot
</h1>

<p align="center">
  <a href="https://github.com/zbytes/rabbitmq-stomp-connection-in-spring-boot/blob/master/LICENSE">
    <img src="https://img.shields.io/badge/license-MIT-blue.svg" />
  </a>
  <img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg" alt="PRs welcome!" />
  <a href="https://twitter.com/intent/follow?screen_name=IAmVuwan">
      <img src="https://img.shields.io/twitter/follow/IAmVuwan.svg?label=Follow%20@IAmVuwan" alt="Follow @IAmVuwan" />
    </a>
</p>

# RabbitMQ STOMP connection in Spring Boot

## Relevant Articles
- https://zbytes.github.io/rabbitmq-stomp-connection-in-spring-boot
- https://medium.com/zbytes/rabbitmq-stomp-connection-in-spring-boot

## To Run

- Build the docker image and run it. (Note: I would prefer docker-compose.yaml)

*Using Dockerfile*

```
docker build -t zbytes/rabbitmq --file deployment/rabbitmq.dockerfile .
docker run -p61613:61613 zbytes/rabbitmq
```

*Using docker-compose.yaml*

```
docker-compose up
```

- Run `ServerRunner` java main class.
- Run `HelloClient` java main class.

### Server Output
```
i.g.zbytes.demo.server.ZbytesController  : Received : Hi zbytes! from: suraj
```
 
### Client Output

```
Received greeting {"from":"suraj","text":"Hi zbytes!"}
```
