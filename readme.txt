1-registry : eureka server
2-product : webapi with h2, eureka client and actuator
3-stock : web api with h2, eureka client and actuator and swagger
4-gateway : auto discovery from eureka, no static routes configured


-Each Microservice has its own DockerFile
-To run the whole project "docker compose up"