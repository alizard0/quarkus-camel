# quarkus-camel
Quarkus application that uses Camel routes

## platform-http via Camel REST DSL
> The Platform HTTP is used to allow Camel to use the existing HTTP server from the rumtime. For example when running Camel on Spring Boot, Quarkus, or other runtimes.

```xml
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-platform-http</artifactId>
    <version>x.x.x</version>
</dependency>
```

The implementation is basically this line of code,
```java
rest()
    .get("/objects")
    .route()
    .setBody()
    .constant(objects)
    .marshal()
    .json()
.endRest();
```

1. For running `HttpPlatformRoute.java` please run:
```bash
$ mvn clean compile quarkus:dev
or,
$ mvn clean package
$ java -jar target/*-runner.jar
```
2. For testing,
```bash
curl localhost:8080/ping
curl localhost:8080/objects
curl -d '{ "key":"value" }' -H "Content-Type: application/json" -X POST localhost:8080/objects 
curl localhost:8080/objects
``` 

### adding microprofile metrics support
```xml
<dependency>
    <groupId>org.apache.camel.quarkus</groupId>
    <artifactId>camel-quarkus-microprofile-metrics</artifactId>
</dependency>
```

1. For fetching microprofile metrics, run the server and do,
```bash
curl localhost:8080/q/metrics
```

### Links
1. https://github.com/apache/camel-quarkus-examples/tree/master/rest-json
1. https://camel.apache.org/components/latest/platform-http-component.html
1. https://camel.apache.org/camel-quarkus/latest/reference/extensions/microprofile-metrics.html