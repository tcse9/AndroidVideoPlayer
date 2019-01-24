package com.taufiq.androidvideoplayer;


import com.taufiq.androidvideoplayer.core.ApplicationSingleton;
import com.taufiq.androidvideoplayer.modules.VideoModule;
/**
 * Application class for testing purpose
 */
public class TestApplication extends ApplicationSingleton {

    private TestBaseComponents mApplicationComponent;

    /**
     * Returns the application dagger components
     * @return
     */
    public TestBaseComponents getOrCreateApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerTestBaseComponents.builder().videoModule(new VideoModule()).build();
        }
        return mApplicationComponent;
    }
}
