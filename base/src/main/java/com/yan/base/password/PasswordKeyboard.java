package com.yan.base.password;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanZi on 2017/5/2.
 * describe：密码输入框，类似于输入框
 * modify:
 * modify date:
 */
public class PasswordKeyboard extends View implements View.OnClickListener{

    private static final String clear_all = "清空";
    private static final String delete = "删除";

    private int screenWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

    //每个键的宽度
    private int keyWidth;
    //每个键的高度
    private int keyHeight;

    private int divLineWidth;

    private Paint mLinePaint;

    //List集合存储Key,方便每次输错都能再次随机数字键盘
    private final List<TextView> keyButtons = new ArrayList<>();

//    private List<String>

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

        divLineWidth = PasswordInputView.dip2px(getContext(), 2);

        keyWidth = (screenWidth - 2 * divLineWidth) / 3;
        keyHeight = (int) (keyWidth * (1 - 0.618));


        if (mLinePaint == null) {
            mLinePaint = new Paint();
            mLinePaint.setColor(Color.parseColor("#cccccc"));
            mLinePaint.setStrokeWidth(divLineWidth);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            setBackground(new ColorDrawable(Color.GRAY));
//        }else{
//            setBackgroundDrawable(new ColorDrawable(Color.GRAY));
//        }

//        for (int i = 0; i < 12; i++) {
//            Button item = new Button(getContext());
//            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(keyWidth, keyHeight);
//            item.setLayoutParams(params);
//            item.setOnClickListener(this);
//            item.setText(keyList.get(i));
//            item.setBackgroundDrawable(getResources().getDrawable(R.drawable.key_selector));
//            //监听"删除"的长按监听事件,完成重复删除操作
//            if (DEL.equals(keyList.get(i))) {
//                item.setOnTouchListener(this);
//            }
//            item.setTag(keyList.get(i));
//            addView(item);
//            keyButtons.add(item);
//        }



    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(screenWidth, (keyHeight + divLineWidth) * 4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 4; i++) {
            //画竖线
            if (i < 2) {
                int index_x = keyWidth *(i+1)+ divLineWidth / 2;
                canvas.drawLine(index_x, 0, index_x, getMeasuredHeight(), mLinePaint);
            }
            //画横线
            int index_y = (divLineWidth + keyHeight) * i + divLineWidth / 2;
            canvas.drawLine(0, index_y, getMeasuredWidth(), index_y, mLinePaint);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
