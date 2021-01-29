# tinyurl-systems-design
Excercise of tiny-url recreation with systems design princpiles and scaling

## Architecture
![Architecture](/architecture.png)

## UI
![Main web UI](/screenshot.png)

## Codebase
I spent one evening to complete it, so it's just a POC without any unit tests, not guaranteed to be working perfectly.
The reason is just to try to prepare a small architecture that would enable me to play around scaling and deploying multiple load balancers, instances etc,
especially for Azure.

Written in:
- Backend: Java 11: Spring Boot (required maven in order to build mvn clean install java applications)
- Frontend: React.js.
- DB: MongoDB + Redis
- Deployed as Docker compose

## Generation logic of shortened URLs
In [Grokking the System Design interview](https://www.educative.io/courses/grokking-the-system-design-interview) there are mentioned mutiple approaches for keys generation.
I decided to come up with a bit more smart solution:
- Generator pre-generates set of keys to use. 
- There is a param that sets amount N of keys to generate in one row
- The generation creates next N permutations starting from the last permutation generated previously.
- The whole generation starts from just "a" and it generates all possible permutations
- When all possible permutations are exhausted of a current length, then next keys are extended by 1 character. 
- When choosing a key for URL, then it is randomly polled from the set of currently available keys
- When too less keys are available (there is a param resposible for its min amount) then the next generation round starts to create N next keys.
- When the URL expires (after 2 weeks) then it is returned back to the pool of keys, so it can be reused.
- Dictionary of possible characters is "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ" (63 characters)

Thanks to this logic, it is possible to create the shortest possible URL shortenings, in reality, depending on its usage, 1-6 characters.

Assuming 500 million new URLs per month and 100:1 read:write ratio,
For 1-5 characters there are 63 + 63^2 + 63^3 + 64^4 + 64^5 = 1.090.773.119 possibilities, the new generated keys are going to be enough for 2 months without expiration.
But the keys are going to expire after 2 weeks and are possible to be reused again.

# High level estimates: 
New URLs 200/s
URL redirections 20K/s
Incoming data 100KB/s
Outgoing data 10MB/s
Storage for 5 years 15TB
Memory for cache 170GB

# Docker compose:
 docker-compose up



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
