# Order System: Docker, Spring Boot, Spring JMS & H2 Database Engine

This application simulates a food-ordering system where (1. a user places an order, (2. the order is processed and saved to a database, and (3. the order is sent to a message queue (using spring-jms) to be consumed by a receipt sending service, which generates a receipt to be theoretically emailed to the user.

# Getting Started

Start the application with the following commands:
```
docker compose build
docker compose up -d
```

## Generating Spring JMS spans

A test user is created at startup. Run the following curl command to create a new order for this user:

- **POST /api/orders/new?{userId}**

```
curl -H "Content-type: application/json" -d "{\"item\": \"coffee\", \"total\": 1}" "http://localhost:8080/api/orders/new?userId=1"
```


Logs are written during the flow of placing an order and generating a receipt, and can be viewed in the console by running `docker logs spring-jms-example`:
```
{"instant":{"epochSecond":1660700477,"nanoOfSecond":29000000},"thread":"http-nio-8080-exec-1","level":"INFO","loggerName":"springJmsExample-logger","message":"Received new order: Order [id=2, user=User [id=1, firstName=John, lastName=Doe, email=jdoe@email.com], item=coffee, placedAt=Wed Aug 17 01:41:17 GMT 2022]","endOfBatch":false,"loggerFqcn":"org.apache.logging.log4j.spi.AbstractLogger","contextMap":{},"threadId":35,"threadPriority":5}
2022-08-17 01:41:17,029 [http-nio-8080-exec-1] INFO  springJmsExample-logger - Received new order: Order [id=2, user=User [id=1, firstName=John, lastName=Doe, email=jdoe@email.com], item=coffee, placedAt=Wed Aug 17 01:41:17 GMT 2022]
{"instant":{"epochSecond":1660700477,"nanoOfSecond":75000000},"thread":"http-nio-8080-exec-1","level":"INFO","loggerName":"springJmsExample-logger","message":"Sending new order to order-queue: Order [id=2, user=User [id=1, firstName=John, lastName=Doe, email=jdoe@email.com], item=coffee, placedAt=Wed Aug 17 01:41:17 GMT 2022]","endOfBatch":false,"loggerFqcn":"org.apache.logging.log4j.spi.AbstractLogger","contextMap":{},"threadId":35,"threadPriority":5}
2022-08-17 01:41:17,075 [http-nio-8080-exec-1] INFO  springJmsExample-logger - Sending new order to order-queue: Order [id=2, user=User [id=1, firstName=John, lastName=Doe, email=jdoe@email.com], item=coffee, placedAt=Wed Aug 17 01:41:17 GMT 2022]
{"instant":{"epochSecond":1660700477,"nanoOfSecond":126000000},"thread":"org.springframework.jms.JmsListenerEndpointContainer#0-1","level":"INFO","loggerName":"springJmsExample-logger","message":"Processing receipt for order ID: 2","endOfBatch":false,"loggerFqcn":"org.apache.logging.log4j.spi.AbstractLogger","contextMap":{},"threadId":27,"threadPriority":5}
2022-08-17 01:41:17,126 [org.springframework.jms.JmsListenerEndpointContainer#0-1] INFO  springJmsExample-logger - Processing receipt for order ID: 2
{"instant":{"epochSecond":1660700477,"nanoOfSecond":127000000},"thread":"org.springframework.jms.JmsListenerEndpointContainer#0-1","level":"INFO","loggerName":"springJmsExample-logger","message":"Emailing receipt for order ID 2: Receipt [user=User [id=1, firstName=John, lastName=Doe, email=jdoe@email.com], total=1.0, items=coffee, placedAt=Wed Aug 17 01:41:17 GMT 2022, estimatedDeliveryTime=Wed Aug 17 01:51:17 GMT 2022]","endOfBatch":false,"loggerFqcn":"org.apache.logging.log4j.spi.AbstractLogger","contextMap":{},"threadId":27,"threadPriority":5}
2022-08-17 01:41:17,127 [org.springframework.jms.JmsListenerEndpointContainer#0-1] INFO  springJmsExample-logger - Emailing receipt for order ID 2: Receipt [user=User [id=1, firstName=John, lastName=Doe, email=jdoe@email.com], total=1.0, items=coffee, placedAt=Wed Aug 17 01:41:17 GMT 2022, estimatedDeliveryTime=Wed Aug 17 01:51:17 GMT 2022]
```

### Other API endpoints:

- **GET /api/orders**: get all orders
```
curl "http://localhost:8080/api/orders"
```
- **GET /api/orders/byUserId?{userID}**: get all orders by user
```
curl "http://localhost:8080/api/orders/byUser?userId=1"
```
- **GET /api/orders/{orderId}**: get a specific order
```
curl "http://localhost:8080/api/orders/1" 
```
- **GET /api/users**: get all users
```
curl "http://localhost:8080/api/users" 
```
- **GET /api/users/{userId}**: get a specific user
```
curl "http://localhost:8080/api/users/1"
```
- **GET /api/users/register**: create a new user
```
curl -H "Content-type: application/json" -d "{\"firstName\": \"test\", \"lastName\": \"test\", \"email\": \"test@email.com\"}" "http://localhost:8080/api/users/register"
```

## Tear down

```
docker compose down
```

## References
- [Getting Started with Spring JMS](https://spring.io/guides/gs/messaging-jms/)
