package com.mig35.homeservice.dagger.app;

import com.mig35.homeservice.dagger.control.ControlComponent;
import com.mig35.homeservice.dagger.control.ControlModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class, RepositoryModule.class, ToastModule.class, ControlElementsModule.class})
@Singleton
public interface AppComponent {

    ControlComponent plus(final ControlModule controlModule);
}