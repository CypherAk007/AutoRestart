package com.example.autorestart;

import android.app.Application;//To get your instance of your context
import android.content.Context;

public class MyApplication extends Application {

    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;//Reff to context
//        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this,MainActivity.class));
    }

    @Override
    public Context getApplicationContext(){
        return super.getApplicationContext();
    }

    public static MyApplication getInstance(){//we create static method to getinstance
        return instance;
    }

}
