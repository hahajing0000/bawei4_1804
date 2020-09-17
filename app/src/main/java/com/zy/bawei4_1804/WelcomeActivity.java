package com.zy.bawei4_1804;

import android.content.Intent;
import android.os.Bundle;

import com.zy.widget.CircleView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author:zhangyue
 * @date:2020/9/17
 */
public class WelcomeActivity extends AppCompatActivity {
    private CircleView cvTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        cvTimer.startAnimal();
        cvTimer.setListener(new CircleView.CircleViewCompletedListener() {
            @Override
            public void completed() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.in_right,R.anim.out_left);
                finish();
            }
        });
    }

    private void initData() {

    }

    private void initView() {
        cvTimer = (CircleView) findViewById(R.id.cv_timer);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cvTimer.destoryView();
    }
}
