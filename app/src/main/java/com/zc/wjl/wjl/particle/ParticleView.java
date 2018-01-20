package com.zc.wjl.wjl.particle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/**
 * @author Wjl.
 * @date 2018\1\20 0020
 */

public class ParticleView extends View {
    private Paint mPaint;
    private Particle[] mParticles;
    private float width, height;


    public ParticleView(Context context) {
        this(context, null);
    }

    public ParticleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParticleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float baseX = width / 2;
        float baseY = height / 2;
        Log.d("widthfucker", "init: " + baseX);
        mParticles = new Particle[300];
        for (int i = 0; i < 300; i++) {
            mParticles[i] = getParticle(baseX, baseY);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawParticle(canvas);
    }

    public void startAnim() {
    }

    private void drawParticle(Canvas canvas) {
        for (Particle particle : mParticles) {
            mPaint.setColor(particle.color);
            canvas.drawCircle(particle.centerX, particle.centerY, particle.radius, mPaint);
        }
    }

    private Particle getParticle(float baseX, float baseY) {
        Particle particle = new Particle();
        float randomX = (float) Math.random();
        float deltaX = baseX * randomX;
        float randomY = (float) Math.random();
        float deltaY = baseY * randomY;
        particle.centerX = ((int) (randomX * 100)) % 2 == 0 ? baseX + deltaX : baseX - deltaX;
        particle.centerY = ((int) (randomY * 100)) % 2 == 0 ? baseY + deltaY : baseY - deltaY;
        particle.radius = (float) (Math.random() * (baseX / 30));
        particle.color = randomColor();
        return particle;
    }

    private class Particle {
        float centerX;
        float centerY;
        float radius;
        int color;
    }

    private int randomColor() {
        return Color.argb(255, (int) ((Math.random() + 0.01) * 256), (int) ((Math.random() + 0.01) * 256), (int) ((Math.random() + 0.01) * 256));
    }
}
