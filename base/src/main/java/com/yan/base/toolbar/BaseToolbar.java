package com.yan.base.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yan.base.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by YanZi on 2017/7/25.
 * describe：
 * modify:
 * modify date:
 */
public  class BaseToolbar extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "BaseToolbar";

    /**
     * 普通状态栏，顶部深色
     */
    public static final int STATUS_BAR_TYPE_NORMAL = 0;
    /**
     * 全屏类型，顶部和标题栏颜色一致
     */
    public static final int STATUS_BAR_TYPE_FULL = STATUS_BAR_TYPE_NORMAL + 1;
    /**
     * 图片普通，图片到达状态栏，顶部加深颜色
     */
    public static final int STATUS_BAR_TYPE_IMG_NORMAL = STATUS_BAR_TYPE_FULL + 1;
    /**
     * 图片全屏，图片到达状态栏
     */
    public static final int STATUS_BAR_TYPE_IMG_FULL = STATUS_BAR_TYPE_IMG_NORMAL + 1;
    /**
     * 类型
     */
    private int baseToolBarType = STATUS_BAR_TYPE_NORMAL;

    /**
     * toolbar
     */
    public Toolbar tb_base_tb;

    /**
     * 自定义的父布局
     */
    public RelativeLayout rl_base_tb;
    public LinearLayout
            /**
             * 搜索的父布局
             */
//            ll_base_tb_search,
            /**
             * 右边的布局
             */
            ll_base_tb_right,
    /**
     * 左边的布局
     */
    ll_base_tb_left;
    /**
     * 右边默认自定义布局的图片
     */
    ImageView iv_base_tb_right;
    /**
     * 右边默认自定义布局的文字
     */
    public TextView tv_base_tb_right,
    /**
     * 标题
     */
    tv_base_tb_title;
    /**
     * 搜索
     */
    public EditText et_base_tb_search;
    /**
     * 监听器
     */
    private BaseToolbarListener baseToolbarListener;

    /**
     * 背景色
     */
    @ColorInt
    int backgroundColor;

    @IntDef({STATUS_BAR_TYPE_NORMAL, STATUS_BAR_TYPE_FULL, STATUS_BAR_TYPE_IMG_NORMAL, STATUS_BAR_TYPE_IMG_FULL})
    @Retention(RetentionPolicy.SOURCE)
    @interface BaseToolBarType {
    }


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


    private void init(Context context, @Nullable AttributeSet attrs) {
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.toolbar_base, this);
        tb_base_tb = (Toolbar) findViewById(R.id.tb_base_tb);
        ll_base_tb_right = (LinearLayout) findViewById(R.id.ll_base_tb_right);
        ll_base_tb_left = (LinearLayout) findViewById(R.id.ll_base_tb_left);
        iv_base_tb_right = (ImageView) findViewById(R.id.iv_base_tb_right);
        tv_base_tb_right = (TextView) findViewById(R.id.tv_base_tb_right);
        tv_base_tb_title = (TextView) findViewById(R.id.tv_base_tb_title);
        rl_base_tb = (RelativeLayout) findViewById(R.id.rl_base_tb);

        //设置 toolbar默认的返回按钮
        tb_base_tb.setNavigationIcon(R.drawable.icon_back);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 根据需求自己设置
//            setElevation(5);
//        }
        //获取自定义属性
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseToolBar);
            baseToolBarType = typedArray.getInteger(R.styleable.BaseToolBar_baseToolBarType, STATUS_BAR_TYPE_NORMAL);
            backgroundColor = typedArray.getColor(R.styleable.BaseToolBar_baseToolBarColor, getResources().getColor(R.color.colorPrimary));
            typedArray.recycle();
        }
        //设置背景色
        setBackgroundColor(backgroundColor);
        
        initOtherLayout(context);
    }

      void initOtherLayout(Context context){};

    /**
     * 标题
     *
     * @param title
     */
    public void setTitleText(String title) {
        tv_base_tb_title.setText(title);
    }

    /**
     * 右侧文字
     *
     * @param right
     */
    public void setDefaultLayoutRightText(String right) {
        tv_base_tb_right.setText(right);
    }

    /**
     * tb_base_tb 的左侧图标
     *
     * @param resId
     */
    public void setToolBarLeftIcon(@DrawableRes int resId) {
        tb_base_tb.setNavigationIcon(resId);
    }

    /**
     * tb_base_tb 的左侧图标
     *
     * @param icon
     */
    public void setToolBarLeftIcon(@Nullable Drawable icon) {
        tb_base_tb.setNavigationIcon(icon);
    }

    /**
     * 设置右侧的图标
     *
     * @param resId
     */
    public void setDefaultLayoutRightIcon(@DrawableRes int resId) {
        iv_base_tb_right.setImageResource(resId);
    }

    /**
     * 设置右侧的默认视图标题
     *
     * @param icon
     */
    public void setDefaultLayoutRightIcon(@Nullable Drawable icon) {
        iv_base_tb_right.setImageDrawable(icon);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_base_tb_right) {
            baseToolbarListener.clickRight();
        }
    }

    /**
     * 获得状态栏的类型
     *
     * @return
     */
    public int getBaseToolBarType() {
        return baseToolBarType;
    }

    public void setBaseToolBarType(@BaseToolBarType int baseToolBarType) {
        this.baseToolBarType = baseToolBarType;
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        super.setBackgroundColor(color);
        this.backgroundColor = color;
    }

 

    /**
     * 获得当前title整体高度
     *
     * @param context
     * @return
     */
    public int getHeight(Context context, boolean hasStatus) {
        int result = 0;
        if (hasStatus) {
            int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resId > 0) {
                result = context.getResources().getDimensionPixelOffset(resId);
            }
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


    /**
     * 左边的自定义布局
     *
     * @param leftChild
     */
    public void setDefaultLayoutLeftView(ViewGroup leftChild) {
        ll_base_tb_left.removeAllViews();
        ll_base_tb_left.addView(leftChild);
    }

    /**
     * 右边自定义布局
     *
     * @param rightChild
     */
    public void setDefaultLayoutRightView(ViewGroup rightChild) {
        ll_base_tb_right.removeAllViews();
        ll_base_tb_right.addView(rightChild);
    }

    /**
     * 获得当前标题栏的背景颜色
     *
     * @return
     */
    public int getBackgroundColor() {
        return backgroundColor;
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

    public interface BaseToolbarListener {

        void clickLeft();

        void clickRight();

    }


}
