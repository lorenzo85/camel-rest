package org.camel.web.api.user;

import org.apache.camel.spring.SpringRouteBuilder;
import org.camel.web.api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.String.*;
import static org.apache.camel.model.rest.RestBindingMode.json;

@Component
public class UserRoutes extends SpringRouteBuilder {

    public static final String APPLICATION_JSON = "application/json";

    @Value("${routes.port}")
    public int port;
    @Value("${routes.hostname}")
    public String hostname;
    @Value("${routes.component}")
    public String component;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .port(port)
                .host(hostname)
                .bindingMode(json)
                .component(component)
                .dataFormatProperty("prettyPrint", "true");

        // CRUD User repository REST Interface
        rest("/user").description("User rest service")
                .post()
                .type(User.class)
                .consumes(APPLICATION_JSON)
                .produces(APPLICATION_JSON)
                .outType(User.class)
                .to(getBeanForMethod("create"))

                .get("/{id}")
                .type(Long.class)
                .produces(APPLICATION_JSON)
                .outType(User.class)
                .to(getBeanForMethod("read"))

                .put("/{id}")
                .type(User.class)
                .consumes(APPLICATION_JSON)
                .produces(APPLICATION_JSON)
                .outType(User.class)
                .to(getBeanForMethod("update"))

                .delete("/{id}")
                .type(Long.class)
                .to(getBeanForMethod("delete"))

                .produces(APPLICATION_JSON)
                .get("/findAll")
                .outTypeList(User.class)
                .to(getBeanForMethod("findAll"));
    }

    private static String getBeanForMethod(String method) {
        return format("bean:userService?method=%s", method);
    }
}
