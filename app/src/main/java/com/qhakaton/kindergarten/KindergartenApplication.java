package com.qhakaton.kindergarten;

import android.app.Application;

import com.sensorberg.sdk.bootstrapper.BackgroundDetector;
import com.sensorberg.sdk.bootstrapper.SensorbergApplicationBootstrapper;

/**
 * Created by Maciej Kozłowski on 20.02.16.
 */
public class KindergartenApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SensorbergApplicationBootstrapper boot = new SensorbergApplicationBootstrapper(this);
        boot.activateService("98541b0b62d0d0efd26b115ea417c4de46179c09b7d757aea4ed9bbab346830a");
        boot.hostApplicationInForeground();

        BackgroundDetector detector = new BackgroundDetector(boot);
        registerActivityLifecycleCallbacks(detector);
    }
}
