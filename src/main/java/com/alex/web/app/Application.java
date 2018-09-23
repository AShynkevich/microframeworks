package com.alex.web.app;

import java.util.Set;

import com.alex.web.configuration.Configurator;
import com.google.inject.Inject;

public class Application {

    @Inject
    private Set<Configurator> configuratorList;

    public void start() {
        configuratorList.forEach(Configurator::configure);
    }
}
