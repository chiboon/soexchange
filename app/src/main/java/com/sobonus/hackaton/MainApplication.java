package com.sobonus.hackaton;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by muhamad.kamal on 13/6/2015.
 */
public class MainApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "rNXoGLQRx9PK14SgukRo53Pgw1gw57ewnXVrrqJJ", "HKRJ6OrXxGxYDpjD9M1str7YHV9RR9WsYaKkyyIK");
    }
}
