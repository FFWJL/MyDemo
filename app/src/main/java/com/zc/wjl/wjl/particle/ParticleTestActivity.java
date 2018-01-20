package com.zc.wjl.wjl.particle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zc.wjl.wjl.R;

/**
 * @author Wjl.
 * @date 2018\1\20 0020
 */

public class ParticleTestActivity extends AppCompatActivity {
    private ParticleView particleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particle_test);
//        particleView = (ParticleView) findViewById(R.id.particle);
    }

    public void start(View view) {
        particleView.startAnim();
    }
}
