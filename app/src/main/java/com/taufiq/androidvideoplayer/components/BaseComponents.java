package com.taufiq.androidvideoplayer.components;

import com.taufiq.androidvideoplayer.MainActivity;
import com.taufiq.androidvideoplayer.modules.VideoModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * {@link BaseComponents} are the components throughout the whole project
 */
@Singleton
@Component (modules = {VideoModule.class})
public interface BaseComponents {

    void inject(MainActivity mainActivity);
}
