---
[api](./doc/api.md) или в [swagger](http://localhost:8080/swagger-ui.html) при поднятом приложении

## F.A.Q.

Q: Как поднять локальо бэк?
A: Запустить команду. Локально должен быть установлен докер
```bash
docker run -d -p 27017:27017 mongo:4.0.25-xenial
./gradlew  clean build -x test jibDockerBuild
 docker run --network "host" -p 8080:8080 medpod/optimal-blood-pressure-backend 
```