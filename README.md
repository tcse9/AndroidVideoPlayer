# AndroidVideoPlayer
- This Android app demonstrates a video player with callback functions for forward, rewind, play and pause. 
- The dummy video used has been taken from in-app raw folder.

## Technology used
- Databindings.
- Dagger 2 as dependency injection.
- Robolectric as unit test.
- ViewModel, Singleton, Observer as basic design pattern.

## Special note
- Several test case is written in [MainActivityTest.java](app/src/test/java/com/taufiq/androidvideoplayer/MainActivityTest.java) class. You can run and test.
- Robolectric takes some extra time while you are executing the test class for the first time only as it downloads necessary dependencies.
