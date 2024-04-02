# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR файл в контейнер
COPY target/kamalov_front-1.0.jar /app/kamalov_front-1.0.jar

# Указываем команду, которая будет запущена при старте контейнера
CMD ["java", "-jar", "kamalov_front-1.0.jar"]