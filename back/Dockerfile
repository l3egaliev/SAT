# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Обновляем список пакетов и устанавливаем пакет apt-transport-https, который позволяет использовать HTTPS-транспорт для репозиториев apt
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    apt-transport-https && \
    rm -rf /var/lib/apt/lists/*

# Устанавливаем драйвер JDBC для PostgreSQL
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    postgresql-client && \
    rm -rf /var/lib/apt/lists/*

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR файл в контейнер
COPY target/kamalov_back-1.0.jar /app/kamalov_back-1.0.jar

# Указываем команду, которая будет запущена при старте контейнера
CMD ["java", "-jar", "kamalov_back-1.0.jar"]