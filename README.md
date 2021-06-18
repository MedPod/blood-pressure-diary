---
[api](./doc/api.md) или в [swagger](http://localhost:8080/swagger-ui.html) при поднятом приложении

## F.A.Q.

Q: Как поднять локальо бэк?
A: Запустить команду. Локально должен быть установлен докер
```bash
./gradlew  clean build -x test jibDockerBuild
docker run -p 8080:8080 medpod/optimal-blood-pressure-backend 
```