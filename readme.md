Для успешного развертывания приложения необхожимо создать базу данных: graph (PostgreSQL)
на порту: 5432 username: graph password: graph
Сам сервер запускается на порту: 8888
Команда для получения jar файла: gradle clean build
Команда для запуска java -jar graph-1.0-SNAPSHOT.jar
Swagger: /swagger-ui.html (Например: http://localhost:8888/api/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config)