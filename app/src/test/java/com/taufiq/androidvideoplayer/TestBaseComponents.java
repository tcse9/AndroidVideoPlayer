package com.taufiq.androidvideoplayer;

import com.taufiq.androidvideoplayer.modules.VideoModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * {@link TestBaseComponents} are the components throughout the whole test project
 */
@Singleton
@Component (modules = {VideoModule.class})
public interface TestBaseComponents {

    //Inject method foe each of the module where they have been implemented
    void inject(MainActivityTest mainActivity);

}