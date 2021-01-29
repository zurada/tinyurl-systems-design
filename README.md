# tinyurl-systems-design
Excercise of tiny-url recreation with systems design princpiles and scaling

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
