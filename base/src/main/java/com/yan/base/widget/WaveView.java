package com.yan.base.widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/8/23.
 * describe：
 * modify:
 * modify date:
 */
public class WaveView extends View {


    //深色水波贝塞尔曲线上的起始点、控制点
    private Point[] mDarkPoints;
    //浅色水波贝塞尔曲线上的起始点、控制点
    private Point[] mLightPoints;

    //贝塞尔曲线点的总个数
    private int mAllPointCount;
    private int mHalfPointCount;

    private boolean showLightWave;
    private boolean lockWave;
    private int darkWaveAnimTime;
    private int lightWaveAnimTime;
    private float waveHeight;
    private int waveNum;
    private int darkWaveColor;
    private int lightWaveColor;
    //深色水波
    private Paint mWavePaint;

    float radius;//贝塞尔曲线半径
    //水波路径
    private Path mWavePath;

    private ValueAnimator mDarkWaveAnimator;

    private ValueAnimator mLightWaveAnimator;

    //深色波浪移动距离
    private float mDarkWaveOffsetX;
    //浅色波浪移动距离
    private float mLightWaveOffsetX;

    private float waveOffsetY;

    int height;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        showLightWave = typedArray.getBoolean(R.styleable.WaveView_showLightWave, true);
        lockWave = typedArray.getBoolean(R.styleable.WaveView_lockWave, true);

        darkWaveAnimTime = typedArray.getInt(R.styleable.WaveView_darkWaveAnimTime, 1000);
        lightWaveAnimTime = typedArray.getInt(R.styleable.WaveView_lightWaveAnimTime, 1000);
        waveNum = typedArray.getInt(R.styleable.WaveView_waveNum, 1);
        waveHeight = typedArray.getDimension(R.styleable.WaveView_waveHeight, 50);

        darkWaveColor = typedArray.getColor(R.styleable.WaveView_darkWaveColor, getResources().getColor(android.R.color.holo_blue_dark));
        lightWaveColor = typedArray.getColor(R.styleable.WaveView_lightWaveColor, getResources().getColor(android.R.color.holo_green_light));
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {

        mWavePaint = new Paint();
        mWavePaint.setAntiAlias(true);
        mWavePaint.setStyle(Paint.Style.FILL);
        // 防抖动
        mWavePaint.setDither(true);

        mWavePath = new Path();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initWavePoints();
        //开始动画
        startWaveAnimator();
    }

    float width;

    private void initWavePoints() {
        //当前波浪宽度
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        float waveWidth = width / waveNum;
        radius = waveWidth / 4;
        //原点 加 辅助点4 个  加上 4个终点
        mAllPointCount = 1 + 8 * waveNum;
//        mHalfPointCount = mAllPointCount / 2;

        float offHeightLight = height - radius / 16;
        float offHeightDark = height;

        mAllPointCount = mAllPointCount + 8;
        Point[][] point = getPoint(0, offHeightLight, offHeightDark);
        mLightPoints = point[0];
        mDarkPoints = point[1];

    }


    /**
     * 从左往右或者从右往左获取贝塞尔点
     *
     * @return
     */
    private Point[][] getPoint(float offsetWidth, float offHeightLight, float offHeightDark) {

        Point[][] points = new Point[2][mAllPointCount];

        float heightRadius = radius * 1 / 8;
        int xOffset = (int) radius / 2;

        points[0][0] = new Point((int) (0 + offsetWidth - 4 * radius), (int) (offHeightLight - heightRadius));
        points[1][0] = new Point((int) (0 + offsetWidth - 4 * radius), (int) (offHeightDark - heightRadius));
        for (int i = 1; i < mAllPointCount; i++) {
            int num = i / 8 - 1;
            switch (i % 8) {
                case 0://起点
                    points[0][i] = new Point((int) (num * 4 * radius + offsetWidth), (int) (offHeightLight - heightRadius));
                    points[1][i] = new Point((int) (num * 4 * radius + offsetWidth), (int) (offHeightDark - heightRadius));
                    break;
                case 1://控制点1
                    points[0][i] = new Point((int) (num * 4 * radius + offsetWidth) + xOffset, (int) (offHeightLight - 2 * heightRadius));
                    points[1][i] = new Point((int) (num * 4 * radius + offsetWidth) + xOffset, (int) offHeightDark);
                    break;
                case 2://终点1
                    points[0][i] = new Point((int) (radius + num * 4 * radius + offsetWidth), (int) (offHeightLight - 2 * heightRadius));
                    points[1][i] = new Point((int) (radius + num * 4 * radius + offsetWidth), (int) offHeightDark);
                    break;
                case 3://控制点2
                    points[0][i] = new Point((int) (radius * 2 + num * 4 * radius + offsetWidth) - xOffset, (int) (offHeightLight - 2 * heightRadius));
                    points[1][i] = new Point((int) (radius * 2 + num * 4 * radius + offsetWidth) - xOffset, (int) offHeightDark);
                    break;
                case 4://终点2
                    points[0][i] = new Point((int) (radius * 2 + num * 4 * radius + offsetWidth), (int) (offHeightLight - heightRadius));
                    points[1][i] = new Point((int) (radius * 2 + num * 4 * radius + offsetWidth), (int) (offHeightDark - heightRadius));
                    break;
                case 5://控制点3
                    points[0][i] = new Point((int) (radius * 2 + num * 4 * radius + offsetWidth) + xOffset, (int) offHeightLight);
                    points[1][i] = new Point((int) (radius * 2 + num * 4 * radius + offsetWidth) + xOffset, (int) (offHeightDark - 2 * heightRadius));
                    break;
                case 6://终点3
                    points[0][i] = new Point((int) (radius * 3 + num * 4 * radius + offsetWidth), (int) offHeightLight);
                    points[1][i] = new Point((int) (radius * 3 + num * 4 * radius + offsetWidth), (int) (offHeightDark - 2 * heightRadius));
                    break;
                case 7://控制点4
                    points[0][i] = new Point((int) (radius * 4 + num * 4 * radius + offsetWidth) - xOffset, (int) offHeightLight);
                    points[1][i] = new Point((int) (radius * 4 + num * 4 * radius + offsetWidth) - xOffset, (int) (offHeightDark - 2 * heightRadius));
                    break;
                case 8://终点4
                    points[0][i] = new Point((int) (radius * 4 + num * 4 * radius + offsetWidth), (int) (offHeightLight - heightRadius));
                    points[1][i] = new Point((int) (radius * 4 + num * 4 * radius + offsetWidth), (int) (offHeightDark - heightRadius));
                    break;
                default:
                    break;
            }
        }

        return points;
    }


    /**
     * 百分比
     *
     * @param percent
     */
    public void setPercent(float percent) {
        if (percent > 100) {
            percent = 100;
        }
        waveOffsetY = percent * height / 100f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final float mWaveOffsetY = waveOffsetY;//this moment offset
        drawLightWave(canvas, mWaveOffsetY);
        drawDarkWave(canvas, mWaveOffsetY);
    }

    /**
     * 绘制深色波浪(贝塞尔曲线)
     *
     * @param canvas
     */
    private void drawDarkWave(Canvas canvas, float mWaveOffsetY) {
        mWavePaint.setColor(darkWaveColor);

        drawWave(canvas, mWavePaint, mDarkPoints, mDarkWaveOffsetX, mWaveOffsetY);
    }


    /**
     * 绘制浅色波浪(贝塞尔曲线)
     *
     * @param canvas
     */
    private void drawLightWave(Canvas canvas, float mWaveOffsetY) {
        mWavePaint.setColor(lightWaveColor);
        //从右向左的水波位移应该被减去

        drawWave(canvas, mWavePaint, mLightPoints, mLightWaveOffsetX, mWaveOffsetY);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawWave(Canvas canvas, Paint paint, Point[] points, float waveOffset, float mWaveOffsetY) {
        mWavePath.reset();
//        quadTo() 二阶
//        mWavePath.cubicTo(); 三阶
        mWavePath.moveTo(points[0].x + waveOffset, points[0].y - mWaveOffsetY);
        for (int i = 1; i < mAllPointCount; i += 2) {
            mWavePath.quadTo(points[i].x + waveOffset, points[i].y - mWaveOffsetY,
                    points[i + 1].x + waveOffset, points[i + 1].y - mWaveOffsetY);
        }
        mWavePath.lineTo(points[mAllPointCount - 1].x + waveOffset, height);
        mWavePath.lineTo(0, height);
        mWavePath.lineTo(points[0].x + waveOffset, points[0].y - mWaveOffsetY);
        canvas.drawPath(mWavePath, paint);
    }


    private void startWaveAnimator() {
        startLightWaveAnimator();
        startDarkWaveAnimator();
    }


    private void startLightWaveAnimator() {
        if (mLightWaveAnimator != null && mLightWaveAnimator.isRunning()) {
            return;
        }
        mLightWaveAnimator = ValueAnimator.ofFloat(0f, 4 * radius);


        mLightWaveAnimator.setDuration(lightWaveAnimTime);
        mLightWaveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mLightWaveAnimator.setInterpolator(new LinearInterpolator());

        mLightWaveAnimator.start();
        mLightWaveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLightWaveOffsetX = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
    }

    private void startDarkWaveAnimator() {

        if (mDarkWaveAnimator != null && mDarkWaveAnimator.isRunning()) {
            return;
        }
        mDarkWaveAnimator = ValueAnimator.ofFloat(0, 4 * radius);

        mDarkWaveAnimator.setDuration(darkWaveAnimTime);
        mDarkWaveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mDarkWaveAnimator.setInterpolator(new LinearInterpolator());

        mDarkWaveAnimator.start();
        mDarkWaveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDarkWaveOffsetX = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

    }

    private void stopWaveAnimator() {
        if (mDarkWaveAnimator != null && mDarkWaveAnimator.isRunning()) {
            mDarkWaveAnimator.cancel();
            mDarkWaveAnimator.removeAllUpdateListeners();
            mDarkWaveAnimator.removeAllListeners();
            mDarkWaveAnimator = null;
        }
        if (mLightWaveAnimator != null && mLightWaveAnimator.isRunning()) {
            mLightWaveAnimator.cancel();
            mLightWaveAnimator.removeAllUpdateListeners();
            mLightWaveAnimator.removeAllListeners();
            mLightWaveAnimator = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopWaveAnimator();
    }

}
