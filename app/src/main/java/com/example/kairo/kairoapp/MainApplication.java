package com.example.kairo.kairoapp;

import android.app.Application;
import android.content.Context;

import com.example.kairo.kairoapp.Helper.LocaleHelper;

/**
 * Created by kairo on 18/05/18.
 */

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext( LocaleHelper.onAttach(base, "en"));
    }
}
