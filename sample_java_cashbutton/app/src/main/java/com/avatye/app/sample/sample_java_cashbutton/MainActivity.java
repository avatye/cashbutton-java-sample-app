package com.avatye.app.sample.sample_java_cashbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.avatye.sdk.cashbutton.CashButtonConfig;
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener;
import com.avatye.sdk.cashbutton.ICashButtonCallback;
import com.avatye.sdk.cashbutton.ui.CashButtonLayout;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private HeaderView component_header_view;

    private LinearLayout ly_inquire_container;
    private Switch sw_notibar_state, sw_cashbutton_notibar_state;

    private CashButtonLayout cashButton;

    Runnable notibarRunnable;
    Runnable cashbuttonAndNotibarRunnable;

    @Override
    public void onBackPressed() {
        if (cashButton != null) {
            cashButton.onBackPressed(new ICashButtonBackPressedListener() {
                @Override
                public void onBackPressed(boolean b) {
                    if (b) {
                        finish();
                    }
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // region { view inflate }
        component_header_view = findViewById(R.id.component_header_view);
        ly_inquire_container = findViewById(R.id.ly_inquire_container);
        sw_notibar_state = findViewById(R.id.sw_notibar_state);
        sw_cashbutton_notibar_state = findViewById(R.id.sw_cashbutton_notibar_state);
        // endregion

        // region { init CashButton }
        initCommon();
        // endregion

        // region {캐시버튼 문의}
        ly_inquire_container.setOnClickListener(this);
        // endregion}

        // region { 노티바 설정 }
        sw_notibar_state.setOnCheckedChangeListener(this);
        // endregion

        // region { 캐시버튼 및 노티바 설정 }
        sw_cashbutton_notibar_state.setOnCheckedChangeListener(this);
        // endregion

    }


    private void initCommon() {
        component_header_view.setBack(view -> onBackPressed());

        CashButtonLayout.init(MainActivity.this, new ICashButtonCallback() {
            @Override
            public void onSuccess(@NotNull CashButtonLayout cashButtonLayout) {
                Log.e("MainActivity", "setCashButtonSwitch --> onSuceess");
                cashButton = cashButtonLayout;
            }

        });

        CashButtonLayout.initInviteInfo("캐시 버튼에서 친구 초대를 사용할 때 사용하는 메시지입니다.");
        sw_cashbutton_notibar_state.setChecked(CashButtonConfig.getCashButtonState());

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ly_inquire_container) {
            // region { 캐시버튼 문의 }
            CashButtonConfig.actionSuggestion(this);
            // endregion
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.sw_notibar_state) {
            notibarRunnable = new Runnable() {
                @Override
                public void run() {
                    CashButtonConfig.setCashButtonNotify(isChecked);
                }
            };
            NotibarWorkRunnable notiR = new NotibarWorkRunnable();
            Thread t = new Thread(notiR);
            t.start();
        } else if (buttonView.getId() == R.id.sw_cashbutton_notibar_state) {
            cashbuttonAndNotibarRunnable = new Runnable() {
                @Override
                public void run() {
                    CashButtonConfig.setCashButtonState(isChecked);
                }
            };
            CashbuttonAndNotibarWorkRunnable cashnotiR = new CashbuttonAndNotibarWorkRunnable();
            Thread t = new Thread(cashnotiR);
            t.start();
        }
    }


    class NotibarWorkRunnable implements Runnable {

        @Override
        public void run() {
            runOnUiThread(notibarRunnable);
        }
    }


    class CashbuttonAndNotibarWorkRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(cashbuttonAndNotibarRunnable);
        }
    }


}