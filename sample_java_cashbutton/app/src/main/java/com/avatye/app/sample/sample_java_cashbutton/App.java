package com.avatye.app.sample.sample_java_cashbutton;

import android.app.Application;

import com.avatye.sdk.cashbutton.CashButtonConfig;
import com.avatye.sdk.cashbutton.CashButtonPosition;
import com.avatye.sdk.cashbutton.core.service.CashNotifyModel;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /** cash-button initializer */
        CashNotifyModel notifyModel = new CashNotifyModel(this, true, "ADBalloonJAVA", 0, 0);
        CashButtonConfig.initializer(this, CashButtonPosition.END, notifyModel);
    }
}
