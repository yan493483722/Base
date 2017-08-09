package com.yan.base.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/7/25.
 * describe：
 * modify:
 * modify date:
 */
public class BaseToolbar extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "BaseToolbar";

    public static final int STATUS_BAR_TYPE_NORMAL = 0;
    public static final int STATUS_BAR_TYPE_FULL = STATUS_BAR_TYPE_NORMAL + 1;
    public static final int STATUS_BAR_TYPE_IMG_NORMAL = STATUS_BAR_TYPE_FULL + 1;
    public static final int STATUS_BAR_TYPE_IMG_FULL = STATUS_BAR_TYPE_IMG_NORMAL + 1;
    private int baseToolBarType = STATUS_BAR_TYPE_NORMAL;
    public Toolbar tb_base_tb;
    LinearLayout ll_base_tb,
            ll_base_tb_search,
            ll_base_tb_right,
            ll_base_tb_left;
    ImageView iv_base_tb_right;
    public TextView tv_base_tb_right, tv_base_tb_title;
    public EditText et_base_tb_search;
    private FrameLayout fl_base_tb_title;
    private BaseToolbarListener baseToolbarListener;
    private
    @ColorInt
    int backgroundColor;

    public BaseToolbar(Context context) {
        super(context);
        init(context, null);
    }

    public BaseToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setBaseToolbarListener(BaseToolbarListener baseToolbarListener) {
        this.baseToolbarListener = baseToolbarListener;
        ll_base_tb_right.setOnClickListener(this);
        tb_base_tb.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseToolbar.this.baseToolbarListener.clickLeft();
            }
        });
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.toolbar_base, this);
        tb_base_tb = (Toolbar) findViewById(R.id.tb_base_tb);
        ll_base_tb_right = (LinearLayout) findViewById(R.id.ll_base_tb_right);
        ll_base_tb_left = (LinearLayout) findViewById(R.id.ll_base_tb_left);
        ll_base_tb = (LinearLayout) findViewById(R.id.ll_base_tb);
        iv_base_tb_right = (ImageView) findViewById(R.id.iv_base_tb_right);
        tv_base_tb_right = (TextView) findViewById(R.id.tv_base_tb_right);
        tv_base_tb_title = (TextView) findViewById(R.id.tv_base_tb_title);
        ll_base_tb_search = (LinearLayout) findViewById(R.id.ll_base_tb_search);
        et_base_tb_search = (EditText) findViewById(R.id.et_base_tb_search);
        fl_base_tb_title = (FrameLayout) findViewById(R.id.fl_base_tb_title);

        tb_base_tb.setNavigationIcon(R.drawable.icon_back);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 根据需求自己设置
//            setElevation(5);
//        }
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseToolBar);
            baseToolBarType = typedArray.getInteger(R.styleable.BaseToolBar_baseToolBarType, STATUS_BAR_TYPE_NORMAL);
            backgroundColor = typedArray.getColor(R.styleable.BaseToolBar_baseToolBarColor, getResources().getColor(R.color.colorPrimary));
            typedArray.recycle();
        }
        setBackgroundColor(backgroundColor);
    }

    public void setTitleText(String title) {
        tv_base_tb_title.setText(title);
    }

    public void setRightText(String right) {
        tv_base_tb_right.setText(right);
    }

    public void setLeftIcon(@DrawableRes int resId) {
        tb_base_tb.setNavigationIcon(resId);
    }

    public void setRightIcon(@DrawableRes int resId) {
        iv_base_tb_right.setImageResource(resId);
    }

    public void setRightIcon(@Nullable Drawable icon) {
        iv_base_tb_right.setImageDrawable(icon);
    }

    public void setLeftIcon(@Nullable Drawable icon) {
        tb_base_tb.setNavigationIcon(icon);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_base_tb_right) {
            baseToolbarListener.clickRight();
        }
    }

    public int getBaseToolBarType() {
        return baseToolBarType;
    }

    public void setBaseToolBarType(int baseToolBarType) {
        this.baseToolBarType = baseToolBarType;
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        super.setBackgroundColor(color);
        this.backgroundColor = color;
    }

    /**
     * 设置成搜索
     */
    public void setSearch() {
        fl_base_tb_title.setLayoutParams(new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,3));
        ll_base_tb_search.setVisibility(VISIBLE);
        tv_base_tb_title.setVisibility(GONE);
    }

    public int getHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        int[] attrs = {android.R.attr.actionBarSize};
        TypedArray values = context.getTheme().obtainStyledAttributes(attrs);
        try {
            result += values.getDimensionPixelSize(0, 0);//第一个参数数组索引，第二个参数 默认值
        } finally {
            values.recycle();
        }
        return result;
    }

    public EditText getEt_base_tb_search() {
        return et_base_tb_search;
    }


    public void setCustomeView(View child) {
        ll_base_tb.removeAllViews();
        ll_base_tb.addView(child);
    }


    public int getBackgroundColor() {
        return backgroundColor;
    }

    public interface BaseToolbarListener {

        void clickLeft();

        void clickRight();

    }


}
