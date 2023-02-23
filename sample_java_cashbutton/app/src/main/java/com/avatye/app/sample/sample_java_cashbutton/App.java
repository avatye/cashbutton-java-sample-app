package com.avatye.app.sample.sample_java_cashbutton;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.avatye.sdk.cashbutton.CashButtonConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * cash-button initializer
         * 현재 연동된 키는 테스트 키 이므로 정식 발급된 키로 변경하셔야 정산이 가능합니다
         */

        CashButtonConfig.initializer(
                this,
                "91e4b7f81a6511ea813d0a4916b2361a",
                "aafc702323bf6a214"
        );

        // setDebugMode(): 디버그 모드 사용 여부
        // 앱 검수 시 : 해당 코드 true 후 전달
        // 앱 배포 시:  false로 변경 또는 제거 후 배포
        CashButtonConfig.setDebugMode(true);
    }
}


//// Android 5.0(Api 21) 미만을 지원하는 앱인 경우
//class AppApplication extends MultiDexApplication {
//}

