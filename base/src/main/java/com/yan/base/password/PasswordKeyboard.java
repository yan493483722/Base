package com.yan.base.password;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by YanZi on 2017/5/2.
 * describe：密码输入框，类似于输入框
 * modify:
 * modify date:
 */
public class PasswordKeyboard extends View {

    private static final String clear_all = "清空";
    private static final String delete = "删除";

    private int screenWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

    //每个键的宽度
    private int keyWidth;
    //每个键的高度
    private int keyHeight;

    private int divLineWidth;

    private Paint mLinePaint;


    //    第一个构造函数：     当不需要使用xml声明或者不需要使用inflate动态加载时候，实现此构造函数即可
    public PasswordKeyboard(Context context) {
        super(context);
        init();
    }

    // 第二个构造函数:     当需要在xml中声明此控件，则需要实现此构造函数。并且在构造函数中把自定义的属性与控件的数据成员连接起来。
    public PasswordKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    // 第三个构造函数：     接受一个style资源
    public PasswordKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PasswordKeyboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


    private void init() {

        keyHeight = keyWidth = (screenWidth - 2 * divLineWidth) / 3;
        divLineWidth = PasswordInputView.dip2px(getContext(), 2);
        if (mLinePaint == null) {
            mLinePaint = new Paint();
            mLinePaint.setColor(Color.parseColor("#cccccc"));
            mLinePaint.setStrokeWidth(divLineWidth);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(screenWidth, (keyHeight + divLineWidth) * 4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画横线
        for (int i = 0; i < 4; i++) {
            int index_y = (divLineWidth + keyHeight) * i;
            canvas.drawLine(0, index_y, getMeasuredWidth(), index_y, mLinePaint);
        }
        //画竖线
        for (int i = 1; i < 3; i++) {
            int index_x = keyWidth * i + divLineWidth * (i - 1);
            canvas.drawLine(index_x, 0, index_x, getMeasuredHeight(), mLinePaint);
        }


    }


}
