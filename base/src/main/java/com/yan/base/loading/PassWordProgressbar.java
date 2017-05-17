package com.yan.base.loading;

import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.yan.base.R;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2017/5/15.
 * describe：
 * modify:
 * modify date:
 */
public class PassWordProgressbar extends View {

    private int barColor;
    private float barWidth;

    private Paint paint;

    private Paint textPaint;
    //最大角度
    private static final float DEFAULT_MAX_ANGLE = -305f;

    //最小的角度
    private static final float DEFAULT_MIN_ANGLE = -19f;

    //圆弧默认颜色
    private final static int DEFAULT_ARC_COLOR = Color.BLUE;
    //圆弧颜色
    private int arcColor = DEFAULT_ARC_COLOR;

    private AnimatorSet animatorSet;

    private boolean isLoading;

    private boolean isShowSuccess;

    private boolean isShowFail;


    private String msg = "加载中";

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
        barWidth = typedArray.getDimension(R.styleable.PassWordProgressbar_barWidth, 60f);
        typedArray.recycle();
    }

    private void init() {
        if (paint == null) {
            paint = new Paint();
            paint.setColor(barColor);
            paint.setStrokeWidth(5);
        }

        if (textPaint == null) {
            textPaint = new Paint();
            textPaint.setColor(Color.BLUE);
            textPaint.setTextSize(Tools.px2sp(getContext(), getResources().getDimension(R.dimen.font_middle)));
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(msg, 0, 0, 0f, 0f, textPaint);
    }

    /**
     * 开始转动
     */
    public void loading() {
        if (!isLoading) {
            isLoading = true;
        }
    }

    /**
     * 显示成功
     */
    public void success(CharSequence successText) {
        if (isLoading) {
            cancelLoading();
        }
        if (!isShowSuccess) {
            isShowSuccess = true;
        }
    }

    /**
     * 显示失败
     */
    public void fail(CharSequence successText) {
        if (isLoading) {
            cancelLoading();
        }
        if (!isShowFail) {
            isShowFail = true;
        }

    }

    /**
     * 取消load
     */
    public void cancelLoading() {
        isLoading = false;
    }

    /**
     * 如果在加载loading 就取消loading 如果再加载success 就取消success 如果在加载 fail 就取消fail
     */
    public void cancelAllLoading() {
        if (isLoading) {
            cancelLoading();
        }
        if (isShowSuccess) {
            cancelSuccess();
        }
        if (isShowFail) {
            cancelFail();
        }
    }

    /**
     * 取消成功的动画
     */
    private void cancelSuccess() {
        isShowSuccess = false;
    }

    /**
     * 取消失败的动画
     */
    private void cancelFail() {
        isShowFail = true;
    }


}
