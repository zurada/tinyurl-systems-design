# tinyurl-systems-design
Excercise of tiny-url recreation with systems design princpiles and scaling

## Codebase
I spent one evening to complete it, so it's just a POC without any unit tests, not guaranteed to be working perfectly.
The reason is just to try to prepare a small architecture that would enable me to play around scaling and deploying multiple load balancers, instances etc,
especially for Azure.

Written in:
- Backend: Java 11: Spring Boot (required maven in order to build mvn clean install java applications)
- Frontend: React.js.
- DB: MongoDB + Redis
- Deployed as Docker compose

## Docker compose:
 docker-compose up


## Architecture
![Architecture](/architecture.png)

## UI
![Main web UI](/screenshot.png)


## Swagger

### Main Api:

[Main api](https://editor.swagger.io/?raw=https://github.com/zurada/tinyurl-systems-design/blob/main/swagger/tiny-url-api.yaml)

### Url Key Manager:
[Url Key Manager](https://editor.swagger.io/?raw=https://github.com/zurada/tinyurl-systems-design/blob/main/swagger/url-key-generator.yaml)

### Url Key Generator:
[Url Key Generator](https://editor.swagger.io/?raw=https://github.com/zurada/tinyurl-systems-design/blob/main/swagger/url-key-manager.yaml)

### TODO (readme updates to come!):
1. Try to use Kubernetes
2. Try to deploy all to Azure and scale!
