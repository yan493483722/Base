package com.yan.base.loading;

import android.animation.AnimatorSet;
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
import android.view.View;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/5/15.
 * describe：
 * modify:
 * modify date:
 */
public class PassWordProgressbar extends View {

    private int barColor;
    private float barStockWidth;


    private int textSize;

    private int textColor;

    private float barRectHeight;

    private Paint paint;
    //http://mikewang.blog.51cto.com/3826268/871765//
    //http://blog.csdn.net/linghu_java/article/details/46404081
    private Paint textPaint;
    private Paint arcPaint;
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

    //圆弧对应的矩形
    private RectF arcRectF;
    //TextView对应的矩形
    private Rect textRect;


    private CharSequence msg = "加载中.....................";

    private float startAngle = -90f;

    private float sweepAngle = 360f;

    private float incrementAngele = 20;

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
            paint.setStrokeWidth(5);
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
        speHeightSize = (int) (barRectHeight + textRect.height() + textPaddingTop + textPaddingBottom+arcPaddingTop+arcPaddingBottom);
        int textTotalWidth = (int) (textRect.width() + textPaddingLeft + textPaddingRight);
        if (textTotalWidth > barRectHeight+arcPaddingLeft+arcPaddingRight) {
            speWidthSize = textTotalWidth;
            float left = (textTotalWidth - barRectHeight) / 2;
            arcRectF.set(left,0 , left + barRectHeight, barRectHeight);
        } else {
            speWidthSize = (int)( barRectHeight+arcPaddingLeft+arcPaddingRight);
            arcRectF.set(0, 0, speWidthSize, speWidthSize);
        }
        setMeasuredDimension(speWidthSize, speHeightSize);  //这里面是原始的大小，需要重新计算可以修改本行
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        setBound();
    }

    private void setBound() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
//        arcRectF.set(paddingLeft + mBorderWidth, paddingTop + mBorderWidth, mResize - paddingLeft - mBorderWidth, mResize - paddingTop - mBorderWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText();
        canvas.drawArc(arcRectF, startAngle, sweepAngle, false, arcPaint);


        canvas.drawText(msg.toString(), textPaddingLeft, barRectHeight + textPaddingTop - textRect.top, textPaint);

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
    public void success(CharSequence text) {
        if (isLoading) {
            cancelLoading();
        }
        setTextRectSize(text);
        if (!isShowSuccess) {
            isShowSuccess = true;
        }
    }

    private void setTextRectSize(CharSequence text) {
        msg = text;
        textPaint.getTextBounds(msg.toString(), 0, msg.length(), textRect);
    }

    /**
     * 显示失败
     */
    public void fail(CharSequence text) {
        if (isLoading) {
            cancelLoading();
        }
        setTextRectSize(text);
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
