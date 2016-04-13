package com.example.jeronimo.aiofit;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by JoseAntonio on 13/04/2016.
 */
public class AioFitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
