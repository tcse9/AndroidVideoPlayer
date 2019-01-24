package com.taufiq.androidvideoplayer.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoModule {


    @Provides
    @Singleton
    public VideoModule init(){

        return this;
    }

}



