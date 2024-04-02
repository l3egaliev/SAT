# Настройка для сервиса back
FROM back as back_service
WORKDIR /back
COPY ./back /back
RUN mvn clean package

# Настройка для сервиса front
FROM front as front_service
WORKDIR /front
COPY ./front /front
RUN mvn clean package

# Настройка для сервиса api-gateway
FROM api-gateway as api_gateway_service
WORKDIR /api-gateway
COPY ./api-gateway /api-gateway
RUN mvn clean package

# Конечный образ, который объединяет все сервисы
FROM ubuntu:latest
COPY --from=back_service /back /back
COPY --from=front_service /front /front
COPY --from=api_gateway_service /api-gateway /api-gateway

# Настройка команд для каждого сервиса
CMD ["java", "-jar", "/back/kamalov_back-1.0.jar"]
CMD ["java", "-jar", "/front/kamalov_front-1.0.jar"]
CMD ["java", "-jar", "/api-gateway/kamalov_gate-way-1.0.jar"]
