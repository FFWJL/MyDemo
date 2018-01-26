package com.zc.wjl.wjl.logisticstrack;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zc.wjl.wjl.R;
import com.zc.wjl.wjl.ScreenUtil;

/**
 * 物流进度
 *
 * @author Wjl
 * @date 2017-11-03
 */

public class OrderTrackView extends View {
    private Paint mPaint;
    private Paint mLinePaint;
    private Paint mFirstPaint;
    private int circleColor;
    private int circleRadius;
    private int firstCircleRadius;
    private int circleWidth;
    private int firstCircleWidth;
    private int lineColor;
    private int lineWidth;
    private int firstColor;
    /**
     * 最下面的节点
     */
    private boolean isShowLine = false;
    /**
     * 最新的节点
     */
    private boolean isFirst = false;

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public void setShowLine(boolean showLine) {
        isShowLine = showLine;
    }

    public void setCircleColor(int color) {
        this.circleColor = color;
    }

    public OrderTrackView(Context context) {
        this(context, null);
    }

    public OrderTrackView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderTrackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LogisticsTrack, defStyle, 0);
        circleColor = a.getColor(R.styleable.LogisticsTrack_circleColor, Color.parseColor("#DBDBDB"));
        circleWidth = a.getDimensionPixelSize(R.styleable.LogisticsTrack_circleWidth, ScreenUtil.dp2px(5, context));
        firstCircleWidth = a.getDimensionPixelSize(R.styleable.LogisticsTrack_firstCircleWidth, ScreenUtil.dp2px(10, context));
        lineColor = a.getColor(R.styleable.LogisticsTrack_lineColor1, Color.parseColor("#DBDBDB"));
        lineWidth = a.getDimensionPixelSize(R.styleable.LogisticsTrack_lineWidth, ScreenUtil.dp2px(1, context));
        firstColor = a.getColor(R.styleable.LogisticsTrack_firstColor, Color.parseColor("#07a836"));
        a.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFirstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFirstPaint.setStyle(Paint.Style.STROKE);
        mFirstPaint.setStrokeWidth(ScreenUtil.dp2px(3, context));
        mFirstPaint.setColor(firstColor);
        mLinePaint.setStrokeWidth(lineWidth);
        mPaint.setColor(circleColor);
        mLinePaint.setColor(lineColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int defaultWidth = 50;
        int width = defaultWidth, height;
        if (MeasureSpec.EXACTLY == widthMode) {
            width = widthSize;
        } else if (MeasureSpec.AT_MOST == widthMode) {
            width = Math.min(defaultWidth, widthSize);
        }
        if (MeasureSpec.EXACTLY == heightMode) {
            height = heightSize;
        } else {
            if (!isShowLine) {
                height = Math.max(defaultWidth, heightSize);
            } else {
                //获取父布局的高度，用来设置线的高度
                View parent = (View) getParent();
                height = parent.getMeasuredHeight();
            }
        }
        setMeasuredDimension(width, height);
    }

    //circleRadius = getMeasuredWidth() / 2 - circleWidth / 2;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circleRadius = circleWidth / 2;
        firstCircleRadius = firstCircleWidth / 2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (isFirst) {
            canvas.drawCircle(paddingLeft + getMeasuredWidth() / 2, paddingTop + getMeasuredWidth() / 2, firstCircleRadius, mFirstPaint);
        } else {
            canvas.drawCircle(paddingLeft + getMeasuredWidth() / 2, paddingTop + getMeasuredWidth() / 2, circleRadius, mPaint);
        }
        if (isShowLine) {
            canvas.drawLine(getMeasuredWidth() / 2, getMeasuredWidth() + 15, getMeasuredWidth() / 2, getMeasuredHeight(), mLinePaint);
        }
    }
}
