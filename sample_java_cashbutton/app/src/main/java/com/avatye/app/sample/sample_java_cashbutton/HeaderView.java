package com.avatye.app.sample.sample_java_cashbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;


public class HeaderView extends RelativeLayout {

    TextView tv_header_title;
    View ly_header_back;


    public HeaderView(Context context) {
        this(context, null);
    }


    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HeaderView);
        initializeViews(typedArray);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HeaderView, defStyle, 0);
        initializeViews(typedArray);
    }

    private void initializeViews(final TypedArray typedArray) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.component_common_header_layout, this);

        // findViewById
        tv_header_title = view.findViewById(R.id.tv_header_title);
        ly_header_back = view.findViewById(R.id.ly_header_back);

        // visible
        ly_header_back.setVisibility(View.GONE);

        //set TypedArray attributes
        String text_string = typedArray.getString(R.styleable.HeaderView_text);
        tv_header_title.setText(text_string);
        typedArray.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setBack(final OnClickListener listener) {
        ly_header_back.setVisibility(View.VISIBLE);
        ly_header_back.setOnClickListener(listener);
    }

}
