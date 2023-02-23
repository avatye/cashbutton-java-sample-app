package com.avatye.app.sample.sample_java_cashbutton;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.avatye.sdk.cashbutton.CashButtonConfig;
import com.avatye.sdk.cashbutton.ICashButtonBackPressedListener;
import com.avatye.sdk.cashbutton.ICashButtonCallback;
import com.avatye.sdk.cashbutton.ui.CashButtonLayout;

public class MainActivity extends AppCompatActivity {

    private HeaderView component_header_view;

    private LinearLayout ly_inquire_container;

    private CashButtonLayout cashButton;

    @Override
    public void onBackPressed() {
        if (cashButton != null) {
            cashButton.onBackPressed(new ICashButtonBackPressedListener() {
                @Override
                public void onBackPressed(boolean isSuccess) {
                    if (isSuccess) {
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
        ly_inquire_container = findViewById(R.id.ly_inquire_container);
        // endregion

        // region { init CashButton }
        initCommon();
        // endregion

        // region { 캐시버튼 문의 }
        ly_inquire_container.setOnClickListener(view -> CashButtonConfig.actionSuggestion(this));
        // endregion

    }


    private void initCommon() {
        CashButtonLayout.init(this, new ICashButtonCallback() {
            @Override
            public void onDashBoardStateChange(@NonNull CashButtonLayout.State state) {

            }

            @Override
            public void onSuccess(CashButtonLayout cashButtonLayout) {
                cashButton = cashButtonLayout;
            }
        }, false);

        component_header_view.setBack(view -> onBackPressed());
    }

}