package com.alex.web;

import com.alex.web.app.Application;
import com.alex.web.app.ApplicationModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.javalin.Javalin;

public class AppRunner {

    private static final int PORT = 7000;

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(PORT);
        Injector injector = Guice.createInjector(new ApplicationModule(app));
        injector.getInstance(Application.class).start();
    }
}
