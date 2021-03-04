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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HeaderView component_header_view;

    private LinearLayout ly_inquire_container;
    private CashButtonLayout cashButton;
    private Boolean isCashButtonState = CashButtonConfig.getCashButtonState();


    @Override
    public void onBackPressed() {
        if (cashButton != null) {
            cashButton.onBackPressed(b -> {
                if (b) {
                    finish();
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
        // endregion

        // region { init CashButton }
        initCommon();
        // endregion

        // region {캐시버튼 문의}
        ly_inquire_container.setOnClickListener(this);
        // endregion}
    }


    private void initCommon() {
        component_header_view.setBack(view -> onBackPressed());
        CashButtonLayout.init(MainActivity.this, new ICashButtonCallback() {
            @Override
            public void onSuccess(@NotNull CashButtonLayout cashButtonLayout) {
                cashButton = cashButtonLayout;
            }
        });

        // region {친구초대 메시지 (커스타미이징)}
        CashButtonConfig.initInviteInfo("캐시 버튼에서 친구 초대를 사용할 때 사용하는 메시지입니다.");
        // endregion
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ly_inquire_container) {
            // region { 캐시버튼 문의 }
            CashButtonConfig.actionSuggestion(this);
            // endregion
        }
    }
}