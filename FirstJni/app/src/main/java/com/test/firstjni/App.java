package com.test.firstjni;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * epaper app
 * Created by Administrator on 2018\8\15 0015.
 */

public class App extends Application {
    private static Context mContext;




    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    public static Context getAppContext() {
        return mContext;
    }




}
