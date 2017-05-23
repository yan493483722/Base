package com.yan.base.loading;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/5/15.
 * describe：
 * modify:
 * modify date:
 */
public class PassWordProgressbar extends View {

    private final static int LOAD_STATUS_NONE = -1;
    private final static int LOAD_STATUS_ARC = 0;
    private final static int LOAD_STATUS_SUCCESS = 1;
    private final static int LOAD_STATUS_FAIL = 2;


    private float startX1;
    private float startY1;
    private float stopX1;
    private float stopY1;
    private float stopX2;
    private float stopY2;


    private int barColor;
    private float barStockWidth;

    private static final int DURATION = 1000;

    private int textSize;

    private int textColor;

    private float barRectHeight;

    private Paint paint;
    //http://mikewang.blog.51cto.com/3826268/871765//
    //http://blog.csdn.net/linghu_java/article/details/46404081
    private Paint textPaint;
    private Paint arcPaint;
    private Paint linePaint;


    //初始化的最小的角度
    private static final float START_ANGLE = -90;
    /**
     * 初始化变化的幅度
     */
    private static float MIN_ANGLE = 60f;


    //圆弧默认颜色
    private final static int DEFAULT_ARC_COLOR = Color.BLUE;
    //圆弧颜色
    private int arcColor = DEFAULT_ARC_COLOR;

    //动画合集
    private AnimatorSet arcAnimatorSet;
    //动画合集
    private AnimatorSet successAnimatorSet;
    //动画合集
    private AnimatorSet failAnimatorSet;

    private int loadingStatus = LOAD_STATUS_NONE;

    //圆弧对应的矩形
    private RectF arcRectF;
    //TextView对应的矩形
    private Rect textRect;


    private CharSequence msg = "加载中。。。";

    /**
     * 初始化的角度
     */
    private float startAngle = START_ANGLE;

    /**
     * 摆动的角度
     */
    private float sweepAngle = MIN_ANGLE - 15;//初始化减小，防止第一帧绘制多次造成卡顿视觉效果

    private float textPaddingLeft, textPaddingRight, textPaddingTop, textPaddingBottom, arcPaddingLeft, arcPaddingRight, arcPaddingTop, arcPaddingBottom;


    //    第一个构造函数：     当不需要使用xml声明或者不需要使用inflate动态加载时候，实现此构造函数即可
    public PassWordProgressbar(Context context) {
        super(context);
        init();
    }

    // 第二个构造函数:     当需要在xml中声明此控件，则需要实现此构造函数。并且在构造函数中把自定义的属性与控件的数据成员连接起来。
    public PassWordProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttr(context, attrs);
        init();
    }

    // 第三个构造函数：     接受一个style资源
    public PassWordProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttr(context, attrs);
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PassWordProgressbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getAttr(context, attrs);
        init();
    }

    /**
     * 获得属性
     *
     * @param context
     * @param attrs
     */
    private void getAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PassWordProgressbar);
        barColor = typedArray.getColor(R.styleable.PassWordProgressbar_barColor, Color.GREEN);
        barStockWidth = typedArray.getDimension(R.styleable.PassWordProgressbar_barStockWidth, 4.0f);
        textSize = typedArray.getDimensionPixelSize(R.styleable.PassWordProgressbar_textSize, 4);
        textColor = typedArray.getColor(R.styleable.PassWordProgressbar_textColor, Color.GREEN);
        barRectHeight = typedArray.getDimension(R.styleable.PassWordProgressbar_barRectWidthHeight, 80f);
        textPaddingLeft = typedArray.getDimension(R.styleable.PassWordProgressbar_textPaddingLeft, 0f);
        textPaddingRight = typedArray.getDimension(R.styleable.PassWordProgressbar_textPaddingRight, 0f);
        textPaddingTop = typedArray.getDimension(R.styleable.PassWordProgressbar_textPaddingTop, 0f);
        textPaddingBottom = typedArray.getDimension(R.styleable.PassWordProgressbar_textPaddingBottom, 0f);
        arcPaddingLeft = typedArray.getDimension(R.styleable.PassWordProgressbar_arcPaddingLeft, 0f);
        arcPaddingRight = typedArray.getDimension(R.styleable.PassWordProgressbar_arcPaddingRight, 0f);
        arcPaddingTop = typedArray.getDimension(R.styleable.PassWordProgressbar_arcPaddingTop, 0f);
        arcPaddingBottom = typedArray.getDimension(R.styleable.PassWordProgressbar_arcPaddingBottom, 0f);
        typedArray.recycle();
    }

    private void init() {
        if (paint == null) {
            paint = new Paint();
            paint.setColor(barColor);
            paint.setStrokeWidth(barStockWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
        }

        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setColor(Color.BLUE);
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(getResources().getDimension(R.dimen.font_middle));
        }

        if (arcPaint == null) {
            arcPaint = new Paint();
            arcPaint.setStrokeWidth(barStockWidth);
            arcPaint.setAntiAlias(true);
            arcPaint.setColor(Color.BLUE);
            arcPaint.setStyle(Paint.Style.STROKE);
        }
        if (arcRectF == null) {
            arcRectF = new RectF();
        }
        if (textRect == null) {
            textRect = new Rect();
            setTextRectSize(msg);
        }

        ColorDrawable colorDrawable = new ColorDrawable(Color.CYAN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(colorDrawable);
        } else {
            setBackgroundDrawable(colorDrawable);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int speWidthSize;
        int speHeightSize;
        speHeightSize = (int) (arcPaddingTop + barRectHeight + arcPaddingBottom + textPaddingTop + textRect.height() + textPaddingBottom);
        int textTotalWidth = (int) (textPaddingLeft + textRect.width() + textPaddingRight);
        float left;
        float top = arcPaddingTop + barStockWidth / 2;
        if (textTotalWidth > arcPaddingLeft + barRectHeight + arcPaddingRight) {//文字宽度大于圆弧，arcPaddingLeft无效了
            speWidthSize = textTotalWidth;
            left = (textTotalWidth + barStockWidth - barRectHeight) / 2;//计算合并后
        } else {//文字宽度小于圆弧以圆弧的宽度为准
            speWidthSize = (int) (barRectHeight + arcPaddingLeft + arcPaddingRight);
            left = arcPaddingLeft + barStockWidth / 2;
        }
        arcRectF.set(left, top, left + barRectHeight - barStockWidth / 2, top + barRectHeight - barStockWidth / 2);
        //边缘间距8分之1
        startX1 = left + barRectHeight / 8;
        startY1 = top + barRectHeight * 5 / 8;
        setMeasuredDimension(speWidthSize, speHeightSize);  //这里面是原始的大小，需要重新计算可以修改本行
        Log.e("yan", "" + "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("yan", "" + "onLayout");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (loadingStatus == LOAD_STATUS_ARC) {
            canvas.drawArc(arcRectF, startAngle, sweepAngle, false, arcPaint);
            if (arcAnimatorSet == null || !arcAnimatorSet.isRunning()) {
                animatorPlay();
            }
        } else if (loadingStatus == LOAD_STATUS_SUCCESS) {
            canvas.drawArc(arcRectF, startAngle, sweepAngle, false, arcPaint);
            //画第一根线
            canvas.drawLine(startX1, startY1, stopX1, stopY1, arcPaint);
            if (halfTack) {
                //防止线条过粗分离
                canvas.drawLine(stopX1 - barStockWidth / 2, stopY1 + barStockWidth / 2, stopX2, stopY2, arcPaint);
            }
        } else if (loadingStatus == LOAD_STATUS_FAIL) {
            canvas.drawArc(arcRectF, startAngle, sweepAngle, false, arcPaint);
            if (arcAnimatorSet == null || !arcAnimatorSet.isRunning()) {
                animatorPlay();
            }
        }

        if (getMeasuredWidth() > barRectHeight + arcPaddingLeft + arcPaddingRight) {
            canvas.drawText(msg.toString(), textPaddingLeft, arcPaddingTop + barRectHeight + arcPaddingBottom + textPaddingTop - textRect.top, textPaint);
        } else {
            canvas.drawText(msg.toString(), (getMeasuredWidth() - textRect.width()) / 2, arcPaddingTop + barRectHeight + arcPaddingBottom + textPaddingTop - textRect.top, textPaint);
        }

    }

    /**
     * 开始播放
     */
    //关于动画的具体解析http://blog.csdn.net/yanbober/article/details/46481171
    private void animatorPlay() {
        Log.e("yan", "" + "animatorPlay");

        if (arcAnimatorSet == null) {
            arcAnimatorSet = getArcAnimator();
        } else {
            if (arcAnimatorSet.isRunning() || arcAnimatorSet.isStarted()) {
                return;
            }
        }
        if (arcAnimatorSet.getListeners() == null || !arcAnimatorSet.getListeners().get(0).equals(arcAnimatorListener)) {
            Log.e("yan", "" + "arcAnimatorSet.getListeners()==null||!arcAnimatorSet.getListeners().get(0).equals(arcAnimator)");
            arcAnimatorSet.addListener(arcAnimatorListener);
        }
        arcAnimatorSet.start();
    }


    private Animator.AnimatorListener arcAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            Log.e("yan", "" + " set onAnimationStart");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.e("yan", "" + " set onAnimationEnd");

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.e("yan", "" + " set onAnimationCancel");
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            Log.e("yan", "" + " set onAnimationRepeat");
        }
    };

    /**
     * 得到圆的的AnimatorSet
     *
     * @return
     */
    private AnimatorSet getArcAnimator() {
        //弧度增大
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(MIN_ANGLE + 5, 360);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
        ValueAnimator startPoint = ValueAnimator.ofFloat(0, 360);
        startPoint.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = (float) animation.getAnimatedValue() + START_ANGLE;
                invalidate();
            }
        });
        startPoint.setDuration(DURATION);
        startPoint.setInterpolator(new AccelerateInterpolator(2));
        //弧度减小，减小多一点，防止增大的最后一帧与减小的第一帧重合，造成卡顿错觉
        ValueAnimator arcAnimator2 = ValueAnimator.ofFloat(360, MIN_ANGLE - 5);
        arcAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        arcAnimator2.setDuration(DURATION);
        arcAnimator2.setInterpolator(new LinearInterpolator());
        ValueAnimator startPoint2 = ValueAnimator.ofFloat(360, 0);
        startPoint2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = 360 - (float) animation.getAnimatedValue() + START_ANGLE;
                invalidate();
            }
        });
        startPoint2.setDuration(DURATION);
        //动画效果，定义动画的变化率，可以使存在的动画效果accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。
        startPoint2.setInterpolator(new LinearInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.play(valueAnimator).with(startPoint);
        set.play(arcAnimator2).with(startPoint2).after(valueAnimator);
        return set;
    }

    boolean halfTack = false;
    private TickAnimation tickAnimation;

    //对勾动画
    private class TickAnimation extends Animation {

        @Override
        protected void applyTransformation(final float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime <= 0.5f) {
                //边缘间距8分之1
//                startX1 = left + barRectHeight / 8;
//                startY1 = top + barRectHeight * 5 / 8;
//                stopX1 = startX1 + barRectHeight *2/8*interpolatedTime * 2;
                stopX1 = startX1 + barRectHeight * 2  * interpolatedTime * 2/ 8;
//                stopY1 = startY1 + barRectHeight *2/8*interpolatedTime * 2;
                stopY1 = startY1 + barRectHeight * 2  * interpolatedTime * 2/ 8;
            } else {
//                stopX2 = stopX1 + barRectHeight * 4 / 8 * (interpolatedTime - 0.5f) * 2;
//                stopY2 = stopY1 - barRectHeight * 6 / 8 * (interpolatedTime - 0.5f) * 2;

                stopX2 = stopX1 + barRectHeight * 4  * (interpolatedTime - 0.5f) * 2/ 8;
                stopY2 = stopY1 - barRectHeight * 6  * (interpolatedTime - 0.5f) * 2/ 8;
                halfTack = true;
            }
            invalidate();
        }
    }

    /**
     * 开始转动
     */
    public void loadArc() {
        if (loadingStatus != LOAD_STATUS_ARC) {
            cancelAllLoading();
            loadingStatus = LOAD_STATUS_ARC;
            postInvalidate();
        }
    }

    /**
     * 显示成功
     */
    public void loadSuccess(CharSequence text) {
//        if(linePaint==null){
//            linePaint=new Paint();
//            linePaint.setColor(barColor);
//            linePaint.setStrokeWidth(barStockWidth);
//            linePaint.setStyle(Paint.Style.STROKE);
//            linePaint.setAntiAlias(true);
//        }
        if (tickAnimation == null) {
            tickAnimation = new TickAnimation();
            tickAnimation.setDuration(DURATION );
            //对勾动画监听
            tickAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //当对勾动画完成后,延迟一秒回掉,不然动画效果不明显
//                        postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        }, 1000);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

        if (loadingStatus != LOAD_STATUS_SUCCESS) {
            cancelAllLoading();
            loadingStatus = LOAD_STATUS_SUCCESS;
            if (text.length() == msg.length()) {
                setTextRectSize(text);
                postInvalidate();
            } else {
                setTextRectSize(text);
                invalidateOtherThread();
            }
        }
    }

    private void invalidateOtherThread() {
        post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
                invalidate();
            }
        });
    }

    private void setTextRectSize(CharSequence text) {
        msg = text;
        if (msg == null) {
            textRect.set(0, 0, 0, 0);
        } else {
            textPaint.getTextBounds(msg.toString(), 0, msg.length(), textRect);
        }
    }

    /**
     * 显示失败
     */
    public void loadFail(CharSequence text) {
        if (loadingStatus != LOAD_STATUS_FAIL) {
            cancelAllLoading();
            loadingStatus = LOAD_STATUS_FAIL;
            if (text.length() == msg.length()) {
                setTextRectSize(text);
                postInvalidate();
            } else {
                setTextRectSize(text);
                invalidateOtherThread();
            }
        }
    }

    /**
     * 取消圆弧
     */
    public void cancelLoadingArc() {
        if (loadingStatus == LOAD_STATUS_ARC && arcAnimatorSet != null && arcAnimatorSet.isRunning()) {
            arcAnimatorSet.cancel();
            loadingStatus = LOAD_STATUS_NONE;
            postInvalidate();
            startAngle = START_ANGLE;
            sweepAngle = MIN_ANGLE - 15;
        }
    }

    /**
     * 如果在加载loading 就取消loading 如果再加载success 就取消success 如果在加载 fail 就取消fail
     */
    public void cancelAllLoading() {
        if (loadingStatus != LOAD_STATUS_NONE) {
            cancelLoadingArc();
            cancelLoadingSuccess();
            cancelLoadingFail();
        }
    }

    /**
     * 取消成功的动画
     */
    private void cancelLoadingSuccess() {
        if (loadingStatus == LOAD_STATUS_ARC && successAnimatorSet != null && successAnimatorSet.isRunning()) {
            successAnimatorSet.cancel();
            loadingStatus = LOAD_STATUS_NONE;
            postInvalidate();
            invalidate();
        }
    }

    /**
     * 取消失败的动画
     */
    private void cancelLoadingFail() {
        if (loadingStatus == LOAD_STATUS_ARC && failAnimatorSet != null && failAnimatorSet.isRunning()) {
            failAnimatorSet.cancel();
            loadingStatus = LOAD_STATUS_NONE;
            postInvalidate();
        }
    }

}
