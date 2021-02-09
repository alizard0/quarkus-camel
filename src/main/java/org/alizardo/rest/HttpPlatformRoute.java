package org.alizardo.rest;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.util.json.JsonObject;

public class HttpPlatformRoute extends RouteBuilder {
    private final Set<JsonObject> objects = Collections.synchronizedSet(new LinkedHashSet<>());

    @Override
    public void configure() throws Exception {
        // ping pong endpoint
        from("platform-http:/ping?httpMethodRestrict=GET").setBody().constant("pong").end();

        // receive json, unmarshal, process, marshal and retrieve json
        from("platform-http:/objects?httpMethodRestrict=GET,POST").choice()
                .when(simple("${header.CamelHttpMethod} == 'GET'")).setBody().constant(objects).endChoice()
                .when(simple("${header.CamelHttpMethod} == 'POST'")).unmarshal()
                .json(JsonLibrary.Jackson, JsonObject.class).process().body(JsonObject.class, objects::add).setBody()
                .constant(objects).endChoice().end().marshal().json();
    }
}
