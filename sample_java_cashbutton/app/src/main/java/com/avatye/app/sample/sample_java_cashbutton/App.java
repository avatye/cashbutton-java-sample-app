package com.avatye.app.sample.sample_java_cashbutton;

import android.app.Application;

import com.avatye.sdk.cashbutton.CashButtonConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /** cash-button initializer */
        CashButtonConfig.initializer(this);
    }
}
