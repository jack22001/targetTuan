package com.jackie.target;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;

public class CustomViewActivity extends BaseActivity {
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_activity);
        ButterKnife.bind(this);
    }
}
