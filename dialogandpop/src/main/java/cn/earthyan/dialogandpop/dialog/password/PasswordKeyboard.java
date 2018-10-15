package cn.earthyan.dialogandpop.dialog.password;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.TextView;

import cn.earthyan.dialogandpop.R;
import cn.earthyan.dialogandpop.util.DpPxUtil;

/**
 * Created by YanZi on 2017/5/2.
 * describe：密码输入框，类似于输入框
 * modify:
 * modify date:
 */
public class PasswordKeyboard extends GridLayout implements View.OnClickListener {

    private static final String CLEAR_ALL = "清空";
    private static final int CLEAR_ALL_TAG = -2;

    private static final String DELETE = "删除";
    private static final int DELETE_TAG = -1;

    private static final int COLUMN_COUNT = 3;
    private static final int ROW_COUNT = 4;


    private int screenWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

    //每个键的宽度
    private int keyWidth;
    //每个键的高度
    private int keyHeight;

    private int divLineWidth;

    private Paint mLinePaint;

    int totalWidth;
    int totalHeight;

    private ClickKeyListener clickKeyListener;

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
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PasswordKeyboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        divLineWidth = DpPxUtil.dip2px(getContext(), 2);
        keyWidth = (screenWidth - 2 * divLineWidth) / 3;
        keyHeight = (int) (keyWidth * (1 - 0.618));
        if (mLinePaint == null) {
            mLinePaint = new Paint();
            mLinePaint.setColor(Color.parseColor("#cccccc"));
            mLinePaint.setStrokeWidth(divLineWidth);
        }
        setColumnCount(COLUMN_COUNT);
        setRowCount(ROW_COUNT);
        for (int i = 0; i < 12; i++) {
            TextView item = new TextView(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(keyWidth, keyHeight);
            item.setLayoutParams(params);
            item.setBackgroundResource(R.drawable.selector_rect_white_gray);
            item.setOnClickListener(this);
            item.setTextColor(getResources().getColor(R.color.font_primary));
            item.setGravity(Gravity.CENTER);
//             item.getPaint().setFakeBoldText(true);
            item.setTextSize(DpPxUtil.px2sp(getContext(), getResources().getDimensionPixelOffset(R.dimen.font_large)));
            //监听"删除"的长按监听事件,完成重复删除操作
            if (i == 9) {
                item.setText(CLEAR_ALL);
                item.setTag(CLEAR_ALL_TAG);
            } else if (i == 10) {
                item.setText(0 + "");
                item.setTag(0);
            } else if (i == 11) {
                item.setText(DELETE);
                item.setTag(DELETE_TAG);
            } else {
                item.setText((char) (i + 49) + "");
                item.setTag(i + 1);
            }

            addView(item);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(screenWidth, (keyHeight + divLineWidth) * ROW_COUNT);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int childTop = divLineWidth;
        int childLeft = 0;
        int childRight;
        int childBottom;
        View childView;
        totalWidth = getMeasuredWidth();
        totalHeight = getMeasuredHeight();
        for (int i = 0; i < childCount; i++) {
            childView = getChildAt(i);
            for (int j = 0; j < ROW_COUNT; j++) {
                if (i / (ROW_COUNT - 1) == j) {
                    childTop = keyHeight * j + divLineWidth * (j + 1);
                    break;
                }
            }
            if (i / (ROW_COUNT - 1) == (ROW_COUNT - 1)) {
                childBottom = totalHeight;
            } else {
                childBottom = childTop + keyHeight;
            }
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (i % COLUMN_COUNT == j) {
                    childLeft = (divLineWidth + keyWidth) * j;
                    break;
                }
            }
            if (i % COLUMN_COUNT == COLUMN_COUNT - 1) {
                childRight = totalWidth;
            } else {
                childRight = childLeft + keyWidth;
            }
            childView.layout(childLeft, childTop, childRight, childBottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画横线
        for (int i = 0; i < ROW_COUNT; i++) {
            int index_y = (divLineWidth + keyHeight) * i + divLineWidth / 2;
            canvas.drawLine(0, index_y, totalWidth, index_y, mLinePaint);
        }
        //画竖线
        for (int i = 0; i < COLUMN_COUNT; i++) {
            int index_x = keyWidth * (i + 1) + i * divLineWidth + divLineWidth / 2;
            canvas.drawLine(index_x, 0, index_x, totalHeight, mLinePaint);
        }
    }

    @Override
    public void onClick(View v) {
        if ((int) v.getTag() == CLEAR_ALL_TAG) {
            if (clickKeyListener != null) {
                clickKeyListener.clickClear();
            }
        } else if ((int) v.getTag() == DELETE_TAG) {
            if (clickKeyListener != null) {
                clickKeyListener.clickDelete();
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if ((int) v.getTag() == i) {
                    if (clickKeyListener != null) {
                        clickKeyListener.clickNum(i);
                    }
                    break;
                }
            }
        }
    }


    public ClickKeyListener getClickKeyListener() {
        return clickKeyListener;
    }

    public void setClickKeyListener(ClickKeyListener clickKeyListener) {
        this.clickKeyListener = clickKeyListener;
    }

    public interface ClickKeyListener {

        void clickDelete();

        void clickClear();

        void clickNum(int num);

    }

}
