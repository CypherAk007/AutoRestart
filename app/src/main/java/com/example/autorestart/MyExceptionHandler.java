package com.example.autorestart;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler{
    private Activity activity;

    public MyExceptionHandler(Activity a){
        activity = a;
    }

    @Override
    public void uncaughtException(@NonNull  Thread t, @NonNull  Throwable e) {
        Intent intent = new Intent(activity,MainActivity.class);
        intent.putExtra("crash",true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
        | Intent.FLAG_ACTIVITY_CLEAR_TASK
        | Intent.FLAG_ACTIVITY_NEW_TASK);

//        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.getInstance().getBaseContext(),0,intent,intent.getFlags());
        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.getInstance().getBaseContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);

        AlarmManager mgr = (AlarmManager) MyApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);//Alarm is defined
        mgr.set(AlarmManager.RTC,System.currentTimeMillis() + 100,pendingIntent);

        activity.finish();

        System.exit(2 );
    }
}
