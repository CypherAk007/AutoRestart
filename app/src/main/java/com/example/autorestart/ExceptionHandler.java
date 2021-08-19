package com.example.autorestart;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler implements
        java.lang.Thread.UncaughtExceptionHandler {
    private final Context myContext;
    private final Class<?> myActivityClass;

    public ExceptionHandler(Context context, Class<?> c) {
        myContext = context;
        myActivityClass = c;
    }

    public void uncaughtException(Thread thread, Throwable exception) {

        int pid = Process.myPid();
        Process.killProcess(pid);
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        System.err.println(stackTrace);// You can use LogCat too
        Intent intent = new Intent(myContext, myActivityClass);
        String s = stackTrace.toString();
        //you can use this String to know what caused the exception and in which Activity
        intent.putExtra("uncaughtException", "Exception is: " + stackTrace.toString());
        intent.putExtra("stacktrace", s);
        myContext.startActivity(intent);
        //for restarting the Activity
        Process.killProcess(Process.myPid());
        System.exit(0);


//        final UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//            public void uncaughtException(Thread thread, Throwable ex) {
//                Intent launchIntent = new Intent(activity().getIntent());
//                PendingIntent pending = PendingIntent.getActivity(CSApplication.getContext(), 0,
//                        launchIntent, activity().getIntent().getFlags());
//                getAlarmManager().set(AlarmManager.RTC, System.currentTimeMillis() + 2000, pending);
//                defaultHandler.uncaughtException(thread, ex);
//            }
//        });
    }}
