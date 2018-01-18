package com.zc.wjl.wjl;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zc.wjl.wjl.adapterdesignpattern.Output220Volt;
import com.zc.wjl.wjl.adapterdesignpattern.VoltObjAdapter;
import com.zc.wjl.wjl.surfaceviewdemo.SurfaceTestActivity;

public class MainActivity extends AppCompatActivity {
    private ObjectAnimator oa;
    private CircleAnimDemo cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        VoltObjAdapter voltObjAdapter = new VoltObjAdapter(new Output220Volt());
//        Toast.makeText(this, "对象适配器模式，结果===" + voltObjAdapter.output5V(), Toast.LENGTH_SHORT).show();
//        VoltClassAdapter voltClassAdapter = new VoltClassAdapter();
//        Toast.makeText(this, "类适配器模式，结果===" + voltClassAdapter.output5V(), Toast.LENGTH_SHORT).show();
        cd = (CircleAnimDemo) findViewById(R.id.circle);
        oa = ObjectAnimator.ofFloat(cd, "angle", 0, 360);
        oa.setDuration(3000);
    }

    public void startSurfaceView(View view) {
//        startActivity(new Intent(this, SurfaceTestActivity.class));
        oa.start();
    }
}
