package com.zc.wjl.wjl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private ObjectAnimator oa;
    private CircleAnimDemo cd;
    private PointF pointF;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        VoltObjAdapter voltObjAdapter = new VoltObjAdapter(new Output220Volt());
//        Toast.makeText(this, "对象适配器模式，结果===" + voltObjAdapter.output5V(), Toast.LENGTH_SHORT).show();
//        VoltClassAdapter voltClassAdapter = new VoltClassAdapter();
//        Toast.makeText(this, "类适配器模式，结果===" + voltClassAdapter.output5V(), Toast.LENGTH_SHORT).show();
        container = (FrameLayout) findViewById(R.id.fl_container);
        cd = (CircleAnimDemo) findViewById(R.id.circle);
        oa = cd.anim();
        pointF = new PointF();
        for (int i = 0; i < 300; i++) {
            container.addView(getView());
        }
        cd.setOnCurrentCenter(new CircleAnimDemo.OnCurrentCenterListener() {
            @Override
            public void currentCenterPoint(float x, float y) {
                if (-1 == x && -1 == y) {
                    return;
                }
                pointF.set(x, y);
                for (int i = 1; i < 301; i++) {
                    ((SparkView) container.getChildAt(i)).setPointF(pointF);
                }
            }
        });
    }

    private SparkView getView() {
        SparkView sparkView = new SparkView(this);
        sparkView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return sparkView;
    }

    public void startSurfaceView(View view) {
//        startActivity(new Intent(this, SurfaceTestActivity.class));
        oa.start();
        for (int i = 1; i < 301; i++) {
            SparkView s = (SparkView) container.getChildAt(i);
            ObjectAnimator anim = s.anim();
            if (!anim.isRunning()) {
                Log.d("nksdlanfqw", "startSurfaceView: ");
                anim.start();
            }
        }
    }
}
