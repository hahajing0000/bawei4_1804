package com.zy.bawei4_1804;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zy.common.BaseApplication;
import com.zy.storage.common.SharePreferenceUtils;
import com.zy.widget.PointView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private ViewPager vpGuideMain;
    private View view1;
    private View view2;
    private View view3;

    private PointView pv1;
    private PointView pv2;
    private PointView pv3;

    private Button btnGotoMain;

    private List<View> views;
    private List<PointView> pointViews;

    private final static String isFirst="isFirst";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Boolean result = (Boolean) SharePreferenceUtils.get(BaseApplication.getContext(), isFirst, false);
        if (result){
            startActivity(new Intent(GuideActivity.this,WelcomeActivity.class));
        }

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        vpGuideMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetPointViews();
                pointViews.get(position).setColor(Color.WHITE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnGotoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreferenceUtils.put(BaseApplication.getContext(),isFirst,true);
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.in_right,R.anim.out_left);
                finish();
            }
        });
    }

    private void initData() {
        vpGuideMain.setAdapter(createPageAdapter());
        if (pointViews!=null&&pointViews.size()>0){
            pointViews.get(0).setColor(Color.WHITE);
        }
    }

    private PagerAdapter createPageAdapter() {
        PagerAdapter adapter= new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = views.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(views.get(position));
            }
        };
        return adapter;
    }

    private void initView() {
        views=new ArrayList<>(3);
        pointViews=new ArrayList<>(3);
        vpGuideMain = (ViewPager) findViewById(R.id.vp_guide_main);

        view1 = getLayoutInflater().inflate(R.layout.view_guide_1, null);
        view2 = getLayoutInflater().inflate(R.layout.view_guide_2, null);
        view3 = getLayoutInflater().inflate(R.layout.view_guide_3, null);

        views.add(view1);
        views.add(view2);
        views.add(view3);

        pv1 = (PointView) findViewById(R.id.pv_1);
        pv2 = (PointView) findViewById(R.id.pv_2);
        pv3 = (PointView) findViewById(R.id.pv_3);

        pointViews.add(pv1);
        pointViews.add(pv2);
        pointViews.add(pv3);


        btnGotoMain = (Button) view3.findViewById(R.id.btn_gotoMain);

    }

    private void resetPointViews(){
        if (pointViews!=null&&pointViews.size()>0){
            for (PointView pv:
                 pointViews) {
                pv.resetColor();
            }
        }
    }
}