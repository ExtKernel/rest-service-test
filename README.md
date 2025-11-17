### Running
1) Deploy [WireMock](https://wiremock.org/) [Docker](https://docs.docker.com/engine/install/) container. 
Make sure the container is listening on localhost:8080
For example:
````
docker run --rm -d \      
  --name wiremock \
  -p 8080:8080 \
  -v <PROJECT-DIR>/src/test/resources/mocks:/home/wiremock \
  wiremock/wiremock:2.35.0
````
2) Install Maven dependencies, build and run!