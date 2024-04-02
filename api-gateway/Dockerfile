# Используем базовый образ OpenJDK
FROM openjdk:17

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR файл из локальной директории в контейнер
COPY target/kamalov_gateway-1.0.jar /app/kamalov_gateway-1.0.jar

# Определяем порт, который будет открыт в контейнере
EXPOSE 8080

# Команда для запуска приложения при старте контейнера
CMD ["java", "-jar", "kamalov_gateway-1.0.jar"]