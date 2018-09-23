package com.alex.web.configuration;

import static io.javalin.apibuilder.ApiBuilder.crud;

import com.alex.web.contoller.UserCrudController;
import com.alex.web.exception.EntityNotFoundException;
import com.google.inject.Inject;
import io.javalin.Javalin;

public class RouteConfigurator implements Configurator {

    @Inject
    private Javalin app;
    @Inject
    private UserCrudController userController;

    @Override
    public void configure() {
        configureRouting();
        configureExceptionMapping();
    }

    private void configureRouting() {
        app.routes(() -> crud("/users/:id", userController));
    }

    private void configureExceptionMapping() {
        app.exception(EntityNotFoundException.class,
                (exception, ctx) -> ctx.result(exception.getMessage()).status(404))
                .error(404, ctx -> ctx.result("Resource is not found"));
    }
}
