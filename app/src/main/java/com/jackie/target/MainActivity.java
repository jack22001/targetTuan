package com.jackie.target;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_animation)
    TextView tvAni;
    @BindView(R.id.tv_custome_view)
    TextView tvCustome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvAni.setOnClickListener(this);
        tvCustome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_animation:
                startActivity(new Intent(MainActivity.this,AnimateActivity.class));
                break;
            case R.id.tv_custome_view:
                startActivity(new Intent(MainActivity.this,CustomViewActivity.class));
                break;
            default:
                break;
        }
    }
}
