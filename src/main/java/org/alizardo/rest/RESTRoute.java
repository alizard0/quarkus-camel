package org.alizardo.rest;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.util.json.JsonObject;

public class RESTRoute extends RouteBuilder {
    private final Set<JsonObject> objects = Collections.synchronizedSet(new LinkedHashSet<>());

    @Override
    public void configure() {
        // ping pong endpoint
        rest().get("/ping").route().setBody().constant("pong").endRest();
        rest().get("/objects").route().setBody().constant(objects).marshal().json().endRest();
        rest().post("objects").route().unmarshal().json(JsonLibrary.Jackson, JsonObject.class).process()
                .body(JsonObject.class, objects::add).setBody().constant(objects).marshal().json().endRest();
    }
}
