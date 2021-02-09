# quarkus-camel
Quarkus application that uses Camel routes

## platform-http
> The Platform HTTP is used to allow Camel to use the existing HTTP server from the rumtime. For example when running Camel on Spring Boot, Quarkus, or other runtimes.

```java
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-platform-http</artifactId>
    <version>x.x.x</version>
    <!-- use the same version as your Camel core version -->
</dependency>
```

The implementation is basically this line of code,
```java
from("platform-http:/ping?httpMethodRestrict=GET").setBody().constant("pong").end();
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

### Links
1. https://github.com/apache/camel-quarkus-examples/tree/master/rest-json
2. https://camel.apache.org/components/latest/platform-http-component.html