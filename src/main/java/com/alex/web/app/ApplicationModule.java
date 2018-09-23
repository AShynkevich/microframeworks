package com.alex.web.app;

import com.alex.web.configuration.Configurator;
import com.alex.web.configuration.RouteConfigurator;
import com.alex.web.contoller.UserCrudController;
import com.alex.web.storage.UserCrudDao;
import com.alex.web.storage.list.UserCrudDaoImpl;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import io.javalin.Javalin;

public class ApplicationModule extends AbstractModule {

    private final Javalin app;

    public ApplicationModule(Javalin app) {
        this.app = app;
    }

    @Override
    protected void configure() {
        initSingleBinder();
        initMultipleBinder();
    }

    private void initSingleBinder() {
        bind(Javalin.class).toInstance(app);
        bind(UserCrudDao.class).to(UserCrudDaoImpl.class);
        bind(UserCrudController.class);
        bind(Application.class);
    }

    private void initMultipleBinder() {
        Multibinder<Configurator> multiBinder = Multibinder.newSetBinder(binder(), Configurator.class);
        multiBinder.addBinding().to(RouteConfigurator.class);
    }
}
