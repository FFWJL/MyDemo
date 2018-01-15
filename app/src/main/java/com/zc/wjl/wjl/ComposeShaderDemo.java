package com.zc.wjl.wjl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**混合着色器Demo
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class ComposeShaderDemo extends View {
    private Paint mPaint;

    public ComposeShaderDemo(Context context) {
        this(context, null);
    }

    public ComposeShaderDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComposeShaderDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        Bitmap batman = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        BitmapShader shader1 = new BitmapShader(logo, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        BitmapShader shader2 = new BitmapShader(batman, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //第一个参数是dst目标，第二个参数是src源，需要关闭硬件加速
        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_IN);
        mPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
    }
}
