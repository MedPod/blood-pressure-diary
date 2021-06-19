---
[api](./doc/api.md) или в [swagger](http://localhost:8080/swagger-ui.html) при поднятом приложении

Каждый билд бэкэнда заливается на docker hub после сборки автоматически.

## F.A.Q.
Q: Как поднять локально бэк?
A: Запустить команду. Локально должен быть установлен докер
```bash
 docker run -d -p 27017:27017 mongo:4.0.25-xenial
 docker run --network "host" medpod/optimal-blood-pressure-backend:latest 
```
ИЛИ с использованием docker-compose
```bash
docker-compose up
```

После этого бэк будет доступен по http://localhost:8080/

Q: Как сбилдить локально бэк самому?
A: Запустить команду.
```bash
./gradlew  clean build
```

Q: Как сбилдить локально docker image бэка самому?
A: Запустить команду. Сбилдится image medpod/optimal-blood-pressure-backend
```bash
./gradlew clean build -x test jibDockerBuild
```