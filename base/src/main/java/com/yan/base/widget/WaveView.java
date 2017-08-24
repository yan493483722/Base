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
import android.util.Log;
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


    //水波路径
    private Path mWavePath;

    private ValueAnimator mDarkWaveAnimator;

    private ValueAnimator mLightWaveAnimator;

    //深色波浪移动距离
    private float mDarkWaveOffset;
    //浅色波浪移动距离
    private float mLightWaveOffset;

    int height;

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
        int yRise = 60;

        float offHeight = height - yRise;

        mAllPointCount=mAllPointCount+8;
        Log.d("yan", "width  " + width + " height " + height + "radius " + radius);

        mLightPoints = getPoint(0, offHeight);


        mDarkPoints = getPoint(0, offHeight);
//        mDarkPoints = new Point[mAllPointCount];
//        for(int i = 0; i < mAllPointCount-4; i++){
//            mDarkPoints[i]=mLightPoints[i+4];
//        }
//        mDarkPoints[ mAllPointCount-4]=  mLightPoints[0];
//        mDarkPoints[ mAllPointCount-3]=  mLightPoints[1];
//        mDarkPoints[ mAllPointCount-2]=  mLightPoints[2];
//        mDarkPoints[ mAllPointCount-1]=  mLightPoints[3];
    }

    float radius;//贝塞尔曲线半径

    /**
     * 从左往右或者从右往左获取贝塞尔点
     *
     * @return
     */
    private Point[] getPoint(float offsetWidth, float offHeight) {

        Point[] points = new Point[mAllPointCount];
        Log.e("yan", "mAllPointCount==" + mAllPointCount);

        float heightRadius = radius * 2 / 5;
        int xOffset = (int) radius * 1 / 2;

        points[0] = new Point((int)(0+offsetWidth-4 * radius), (int) (offHeight - heightRadius));
        for (int i = 1; i < mAllPointCount; i++) {
            int num = i / 8-1;
            switch (i % 8) {
                case 0://起点
                    points[i] = new Point((int) (num * 4 * radius+offsetWidth), (int) (offHeight - heightRadius));
                    break;
                case 1://控制点1
                    points[i] = new Point((int) (num * 4 * radius +offsetWidth) + xOffset, (int) (offHeight - 2 * heightRadius));
                    break;
                case 2://终点1
                    points[i] = new Point((int) (radius + num * 4 * radius+offsetWidth), (int) (offHeight - 2 * heightRadius));
                    break;
                case 3://控制点2
                    points[i] = new Point((int) (radius * 2 + num * 4 * radius+offsetWidth) - xOffset, (int) (offHeight - 2 * heightRadius));
                    break;
                case 4://终点2
                    points[i] = new Point((int) (radius * 2 + num * 4 * radius+offsetWidth), (int) (offHeight - heightRadius));
                    break;
                case 5://控制点3
                    points[i] = new Point((int) (radius * 2 + num * 4 * radius+offsetWidth) + xOffset, (int) offHeight);
                    break;
                case 6://终点3
                    points[i] = new Point((int) (radius * 3 + num * 4 * radius+offsetWidth), (int) offHeight);
                    break;
                case 7://控制点4
                    points[i] = new Point((int) (radius * 4 + num * 4 * radius+offsetWidth) - xOffset, (int) offHeight);
                    break;
                case 8://终点4
                    points[i] = new Point((int) (radius * 4 + num * 4 * radius+offsetWidth), (int) (offHeight - heightRadius));
                    break;
                default:
                    break;
            }
            Log.e("yan", "points x==" + points[i].x + "points y==" + points[i].y);
        }

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
        drawWave(canvas, mWavePaint, mDarkPoints, mDarkWaveOffset);
    }

    /**
     * 绘制浅色波浪(贝塞尔曲线)
     *
     * @param canvas
     */
    private void drawLightWave(Canvas canvas) {
        mWavePaint.setColor(lightWaveColor);
        //从右向左的水波位移应该被减去
        drawWave(canvas, mWavePaint, mLightPoints, mLightWaveOffset);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void drawWave(Canvas canvas, Paint paint, Point[] points, float waveOffset) {
        mWavePath.reset();
//        quadTo() 二阶
//        mWavePath.cubicTo(); 三阶

//        float height = lockWave ? 0 : mRadius - 2 * mRadius * mPercent;
        //moveTo和lineTo绘制出水波区域矩形
        mWavePath.moveTo(points[0].x + waveOffset, points[0].y);

//        for (int i = 1; i < mAllPointCount; i += 2) {
        for (int i = 0; i < mAllPointCount; i++) {
            if (i % 2 == 0) {
//                mWavePath.moveTo(points[i].x + waveOffset, points[i].y);
            } else {
                mWavePath.quadTo(points[i].x + waveOffset, points[i].y,
                        points[i + 1].x + waveOffset, points[i + 1].y);
            }

        }
//        Log.e("yan","points[mAllPointCount - 1].x "+points[mAllPointCount - 1].x+" points[mAllPointCount - 1].y "+points[mAllPointCount - 1].y);
//        mWavePath.moveTo(points[mAllPointCount - 1].x, points[mAllPointCount - 1].y);
//        Log.e("yan","points[mAllPointCount - 1].x "+points[mAllPointCount - 1].x+" height "+height);
        mWavePath.lineTo(points[mAllPointCount - 1].x+waveOffset, height);
//        Log.e("yan","  "+0+" height "+height);
        mWavePath.lineTo(0, height);
        mWavePath.lineTo(points[0].x+ waveOffset, points[0].y);


        //mWavePath.lineTo(points[mAllPointCount - 1].x, points[mAllPointCount - 1].y + height);
        //不管如何移动，波浪与圆路径的交集底部永远固定，否则会造成上移的时候底部为空的情况
//        mWavePath.lineTo(points[mAllPointCount - 1].x, height);
//        mWavePath.lineTo(points[0].x, height);
//        mWavePath.close();
        //取该圆与波浪路径的交集，形成波浪在圆内的效果

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
        mLightWaveAnimator = ValueAnimator.ofFloat(0, 4 * radius );
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
        mDarkWaveAnimator = ValueAnimator.ofFloat(0, 4 * radius);
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

    private void stopWaveAnimator() {
        if (mDarkWaveAnimator != null && mDarkWaveAnimator.isRunning()) {
            mDarkWaveAnimator.cancel();
            mDarkWaveAnimator.removeAllUpdateListeners();
            mDarkWaveAnimator = null;
        }
        if (mLightWaveAnimator != null && mLightWaveAnimator.isRunning()) {
            mLightWaveAnimator.cancel();
            mLightWaveAnimator.removeAllUpdateListeners();
            mLightWaveAnimator = null;
        }
    }

}
