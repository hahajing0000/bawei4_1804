package com.zy.bll_usercenter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;

import com.zy.widget.BulletScreenView;
import com.zy.widget.entity.BulletItem;

import java.util.Random;

public class BulletScreenActivity extends AppCompatActivity {
    private BulletScreenView bsvMain;

    String[] contents={
            "111111",
            "22222222222222",
            "333333333333333333",
            "444444444444444444444",
            "55555"
    };

    int[] colors={Color.parseColor("#003366"),
        Color.parseColor("#FF0033"),
            Color.parseColor("#FF3366"),
            Color.parseColor("#FF0099"),
            Color.parseColor("#00FF33"),
    };

    Random mRandom=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bullet_screen);

        bsvMain = (BulletScreenView) findViewById(R.id.bsv_main);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    bsvMain.addItem(new BulletItem(contents[mRandom.nextInt(contents.length)],
                            colors[mRandom.nextInt(colors.length)],
                            mRandom.nextInt(30),
                            mRandom.nextInt(10),null,0,0));

                    SystemClock.sleep(500);
                }
            }
        }).start();
    }
}