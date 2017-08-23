package com.yan.base.widget;

import android.animation.Animator;
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
    private int waveDirect;
    //深色水波
    private Paint mWavePaint;


    //水波路径
    private Path mWaveLimitPath;
    private Path mWavePath;

    private ValueAnimator mDarkWaveAnimator;

    private ValueAnimator mLightWaveAnimator;

    //深色波浪移动距离
    private float mDarkWaveOffset;
    //浅色波浪移动距离
    private float mLightWaveOffset;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Wave);
        showLightWave = typedArray.getBoolean(R.styleable.Wave_showLightWave, true);
        lockWave = typedArray.getBoolean(R.styleable.Wave_lockWave, true);

        darkWaveAnimTime = typedArray.getInt(R.styleable.Wave_darkWaveAnimTime, 1000);
        lightWaveAnimTime = typedArray.getInt(R.styleable.Wave_lightWaveAnimTime, 1000);
        waveNum = typedArray.getInt(R.styleable.Wave_waveNum, 1);
        waveHeight = typedArray.getDimension(R.styleable.Wave_waveHeight, 50);

        darkWaveColor = typedArray.getResourceId(R.styleable.Wave_darkWaveColor, getResources().getColor(android.R.color.holo_blue_dark));
        lightWaveColor = typedArray.getResourceId(R.styleable.Wave_lightWaveColor, getResources().getColor(android.R.color.holo_green_light));

        waveDirect = typedArray.getInt(R.styleable.Wave_waveDirect, 0);
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {

        mWavePaint = new Paint();
        mWavePaint.setAntiAlias(true);
        mWavePaint.setStyle(Paint.Style.FILL);

        mWaveLimitPath = new Path();
        mWavePath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        initWavePoints();
        //开始动画
        startWaveAnimator();
    }

    private void initWavePoints() {
        //当前波浪宽度
        float waveWidth = getWidth() / waveNum;
        mAllPointCount = 8 * waveNum + 1;
        mHalfPointCount = mAllPointCount / 2;
        mDarkPoints = getPoint(waveDirect, waveWidth);
        mLightPoints = getPoint(waveDirect, waveWidth);
    }

    int  height=getHeight() / 2;
    /**
     * 从左往右或者从右往左获取贝塞尔点
     *
     * @return
     */
    private Point[] getPoint(int waveDirect, float waveWidth) {

        height=getHeight() / 2;

        Point[] points = new Point[mAllPointCount];
        //第1个点特殊处理，即数组的中点

        points[mHalfPointCount] = new Point(getWidth() / 2, height);

//        if (waveDirect == 0) {
//
//        } else {
//
//        }

        //屏幕内的贝塞尔曲线点
        for (int i = mHalfPointCount + 1; i < mAllPointCount; i += 4) {
            float width = points[mHalfPointCount].x + waveWidth * (i / 4 - waveNum);
            points[i] = new Point((int) (waveWidth / 4 + width),(int) ( getHeight() / 2 - waveHeight));
            points[i + 1] = new Point((int) (waveWidth / 2 + width), getHeight() / 2);
            points[i + 2] = new Point((int) (waveWidth * 3 / 4 + width), (int) (getHeight() / 2 - waveHeight));
            points[i + 3] = new Point((int) (waveWidth + width), getHeight() / 2);
        }
        //屏幕外的贝塞尔曲线点
        for (int i = 0; i < mHalfPointCount; i++) {
            int reverse = mAllPointCount - i - 1;
            points[i] = new Point(2  * points[mHalfPointCount].x - points[reverse].x,
                    points[mHalfPointCount].y * 2 - points[reverse].y);
        }
        //对从右向左的贝塞尔点数组反序，方便后续处理
        return points;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLightWave(canvas);
        drawDarkWave(canvas);
    }

    /**
     * 绘制深色波浪(贝塞尔曲线)
     *
     * @param canvas
     */
    private void drawDarkWave(Canvas canvas) {
        mWavePaint.setColor(darkWaveColor);
        drawWave(canvas, mWavePaint, mDarkPoints,mDarkWaveOffset);
    }

    /**
     * 绘制浅色波浪(贝塞尔曲线)
     *
     * @param canvas
     */
    private void drawLightWave(Canvas canvas) {
        mWavePaint.setColor(lightWaveColor);
        //从右向左的水波位移应该被减去
        drawWave(canvas, mWavePaint, mLightPoints,mLightWaveOffset);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawWave(Canvas canvas, Paint paint, Point[] points, float waveOffset) {
        mWaveLimitPath.reset();
        mWavePath.reset();
//        float height = lockWave ? 0 : mRadius - 2 * mRadius * mPercent;
        //moveTo和lineTo绘制出水波区域矩形
        mWavePath.moveTo(points[0].x+waveOffset , points[0].y );

        for (int i = 1; i < mAllPointCount; i += 2) {
            mWavePath.quadTo(points[i].x+waveOffset , points[i].y ,
                    points[i + 1].x +waveOffset, points[i + 1].y );
        }
        //mWavePath.lineTo(points[mAllPointCount - 1].x, points[mAllPointCount - 1].y + height);
        //不管如何移动，波浪与圆路径的交集底部永远固定，否则会造成上移的时候底部为空的情况
        mWavePath.lineTo(points[mAllPointCount - 1].x, height);
        mWavePath.lineTo(points[0].x, height);
        mWavePath.close();
        mWaveLimitPath.addCircle(getWidth()/2,getHeight()/2, height, Path.Direction.CW);
        //取该圆与波浪路径的交集，形成波浪在圆内的效果
        mWaveLimitPath.op(mWavePath, Path.Op.INTERSECT);
        canvas.drawPath(mWaveLimitPath, paint);
    }


    private void startWaveAnimator() {
        startLightWaveAnimator();
        startDarkWaveAnimator();
    }


    private void startLightWaveAnimator() {
        if (mLightWaveAnimator != null && mLightWaveAnimator.isRunning()) {
            return;
        }
        mLightWaveAnimator = ValueAnimator.ofFloat(0, 2 *getWidth()/2);
        mLightWaveAnimator.setDuration(lightWaveAnimTime);
        mLightWaveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mLightWaveAnimator.setInterpolator(new LinearInterpolator());
        mLightWaveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLightWaveOffset = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mLightWaveAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mLightWaveOffset = 0;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mLightWaveAnimator.start();
    }

    private void startDarkWaveAnimator() {
        if (mDarkWaveAnimator != null && mDarkWaveAnimator.isRunning()) {
            return;
        }
        mDarkWaveAnimator = ValueAnimator.ofFloat(0, 2 *getWidth()/2);
        mDarkWaveAnimator.setDuration(darkWaveAnimTime);
        mDarkWaveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mDarkWaveAnimator.setInterpolator(new LinearInterpolator());
        mDarkWaveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDarkWaveOffset = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mDarkWaveAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mDarkWaveOffset = 0;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mDarkWaveAnimator.start();
    }

}
