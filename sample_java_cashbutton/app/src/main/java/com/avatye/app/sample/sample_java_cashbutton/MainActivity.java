package com.avatye.app.sample.sample_java_cashbutton;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.avatye.sdk.cashbutton.CashButtonConfig;
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener;
import com.avatye.sdk.cashbutton.ICashButtonCallback;
import com.avatye.sdk.cashbutton.ui.CashButtonLayout;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private HeaderView component_header_view;

    private LinearLayout ly_inquire_container;
    private Switch sw_cashbutton_state, sw_notibar_state;

    private CashButtonLayout cashButtonLayout;

    @Override
    public void onBackPressed() {
        if (cashButtonLayout != null) {
            cashButtonLayout.onBackPressed(new ICashButtonBackPressedListener() {
                @Override
                public void onBackPressed(boolean b) {
                    if (b) {
                        finish();
                    }
                }
            });
        } else {
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // region { findViewById }
        component_header_view = findViewById(R.id.component_header_view);
        sw_cashbutton_state = findViewById(R.id.sw_cashbutton_state);
        sw_notibar_state = findViewById(R.id.sw_notibar_state);
        ly_inquire_container = findViewById(R.id.ly_inquire_container);
        // endregion

        // region { init CashButton }
        initCommon();
        // endregion

        // region { 캐시버튼 설정 }
        sw_cashbutton_state.setOnCheckedChangeListener(this);
        // endregion

        // region { 노티바 설정 }
        sw_notibar_state.setOnCheckedChangeListener(this);
        // endregion

        // region { 캐시버튼 문의 }
        ly_inquire_container.setOnClickListener(view -> CashButtonConfig.actionSuggestion(this));
        // endregion

    }


    private void initCommon() {
        CashButtonLayout.init(MainActivity.this, new ICashButtonCallback() {
            @Override
            public void onSuccess(@Nullable CashButtonLayout cashButtonLayout) {
                MainActivity.this.cashButtonLayout = cashButtonLayout;
            }
        });

        component_header_view.setBack(view -> onBackPressed());

        CashButtonConfig.initInviteInfo("캐시 버튼에서 친구 초대를 할 때 사용하는 메시지입니다.");

        sw_cashbutton_state.setChecked(CashButtonConfig.getCashButtonState());
        sw_notibar_state.setChecked(CashButtonConfig.getAllowNotificationBar());
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.sw_cashbutton_state) {
            if (isChecked) {
                CashButtonConfig.setCashButtonSnoozeOff();
            } else {
                CashButtonConfig.setCashButtonSnoozeOn(1);
            }
        } else if (buttonView.getId() == R.id.sw_notibar_state) {
            CashButtonConfig.setAllowNotificationBar(this, isChecked);
        }
    }

}