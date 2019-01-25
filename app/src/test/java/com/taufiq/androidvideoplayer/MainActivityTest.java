package com.taufiq.androidvideoplayer;

import com.taufiq.androidvideoplayer.modules.VideoModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.junit.Assert.*;



@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE, sdk = 26, application = TestApplication.class)
public class MainActivityTest{


    @Inject
    VideoModule videoModule;
    TestApplication app;

    @Before
    public void before(){

        app = (TestApplication) RuntimeEnvironment.application;
        app.getOrCreateApplicationComponent().inject(this);

    }


    /**
     * This method simply checks if the method returns null value or not;
     */

    @Test
    public void testForwardBackwardCallback(){

        assertNotNull(videoModule.getOnForwardBackwardCall());
    }


    /**
     * This method simply checks if the method returns null value or not;
     */

    @Test
    public void testIsPausedPlayCallBack(){
        assertNotNull(videoModule.getOnPausedCalled());
    }

}