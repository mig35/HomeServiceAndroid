package com.mig35.homeservice.dagger.app;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class, RepositoryModule.class})
@Singleton
public interface AppComponent {

}