package com.taufiq.androidvideoplayer.components;

import com.taufiq.androidvideoplayer.modules.VideoModule;

import javax.inject.Singleton;

import dagger.Component;
import viewmodels.ContentViewModel;


/**
 * {@link BaseComponents} are the components throughout the whole project
 */
@Singleton
@Component (modules = {VideoModule.class})
public interface BaseComponents {
    VideoModule init();

    void inject(ContentViewModel contentViewModel);
}
