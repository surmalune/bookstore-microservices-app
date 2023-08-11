# RESTful E-commerce WebApp: Bookstore

This project was created for educational purposes in order to try out various web development tools and try yourself in designing a microservice architecture. I tried to make the code conform to the principles of *SOLID*, and I also used *Domain Driven Design*. These microservices implement a secure *RESTful API* with *HATEOAS*. *OAuth2*, *OpenID*, and *JWT* are used for authorization and authentication.<br>

## Tools

- ELK (ElasticSearch, Logstash, Kibana) for logging;
- Apache Kafka for real-time messaging;
- Keycloak (with Postgres) and Spring Security for authorization and authentication;
- Spring Cloud for discovery, gateway, config services and feign clients;
- Spring Boot, Spring Data, Spring Hateoas;
- Postgres;
- JPA, Hibernate;
- Lombok;
- MapStruct.

## Services

- **_discovery-service_**: Simplifies communication between services, also facilitates routing for the gateway. Requires authentication.
- **_config-service_**: Allows to more flexibly and conveniently set configuration files for different services.
- **_gateway-service_**: Accepts all requests and sends them to the appropriate service.
- **_catalog-service_**: It is a resource server that contains a catalog of books. Allows everyone to view books, but edit and add them only to authenticated sellers.
- **_cart-service_**: Strictly requires authentication for any requests. For unauthenticated users, the ability to store the cart in the frontend in localStorage will be added later.

## Tools I plan to use

- JUnit, Mockito, TestContainers
- Spring Cloud Sleuth
- Spring Cloud Circuit Breaker
- Resilience4j
- Prometheus, Grafana
- Caffeine, Redis

## Services I plan to add

- inventory-service
- wishlist-service
- checkout-service
- payment-service
- order-service
- customer-service
- delivery-service
- recommendation-service
- review-service
- notification-service
- search-service
- promotion-service
- analytics-service
