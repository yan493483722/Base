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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yan.base.R;
import com.yan.base.uitls.Tools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by YanZi on 2017/7/25.
 * describe：
 * modify:
 * modify date:
 */
public class BaseToolbar extends LinearLayout implements View.OnClickListener {

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
    @BaseToolBarType
    private int baseToolBarType = STATUS_BAR_TYPE_NORMAL;

    /**
     * toolbar
     */
    public Toolbar tb_base_tb;

    /**
     * 自定义的父布局
     */
    public RelativeLayout rl_base_tb;

    /**
     * 左右边的布局
     */
    public LinearLayout ll_base_tb_left, ll_base_tb_right;

    /**
     * 左右边默认自定义布局的图片
     */
    ImageView iv_base_tb_left, iv_base_tb_right;
    /**
     * 右边默认自定义布局的文字
     */
    public TextView tv_base_tb_left, tv_base_tb_right,
    /**
     * 标题
     */
    tv_base_tb_title;

    protected FrameLayout fl_base_tb;
    /**
     * 监听器
     */
    protected BaseToolbarListener baseToolbarListener;

    /**
     * 背景色
     */
    @ColorInt
    int backgroundColor;

    @DrawableRes
    int backgroundDrawableRes;

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

        tv_base_tb_left = (TextView) findViewById(R.id.tv_base_tb_left);
        iv_base_tb_left = (ImageView) findViewById(R.id.iv_base_tb_left);
        tv_base_tb_right = (TextView) findViewById(R.id.tv_base_tb_right);
        iv_base_tb_right = (ImageView) findViewById(R.id.iv_base_tb_right);

        fl_base_tb = (FrameLayout) findViewById(R.id.fl_base_tb);

        tv_base_tb_title = (TextView) findViewById(R.id.tv_base_tb_title);
        rl_base_tb = (RelativeLayout) findViewById(R.id.rl_base_tb);

        tv_base_tb_left.setOnClickListener(this);
        iv_base_tb_left.setOnClickListener(this);
        tv_base_tb_right.setOnClickListener(this);
        iv_base_tb_right.setOnClickListener(this);


        //设置 toolbar默认的返回按钮
        tb_base_tb.setNavigationIcon(R.drawable.icon_back);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 根据需求自己设置
//            setElevation(5);
//        }
        //获取自定义属性
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseToolbar);
            baseToolBarType = typedArray.getInteger(R.styleable.BaseToolbar_baseToolBarType, STATUS_BAR_TYPE_NORMAL);
            backgroundColor = typedArray.getColor(R.styleable.BaseToolbar_baseToolBarColor, getResources().getColor(R.color.colorPrimary));
            backgroundDrawableRes = typedArray.getResourceId(R.styleable.BaseToolbar_baseToolBarDrawableRes, 0);
            typedArray.recycle();
        }
        //设置背景色
        setBackgroundColor(backgroundColor);
        if (backgroundDrawableRes != 0) {
            setBackgroundDrawable(getResources().getDrawable(backgroundDrawableRes));
        }

        initOtherLayout(context);
    }

    void initOtherLayout(Context context) {

    }

    /**
     * 标题
     *
     * @param title
     */
    public void setTitleText(String title) {
        tv_base_tb_title.setText(title);
    }


    /**
     * 右侧文字,图片
     * 覆写此方法 ，设置左侧
     *
     * @param resId
     * @param left
     */
    public void setDefaultLayoutLeft(@DrawableRes int resId, String left) {
        if (Tools.isNull(left)) {
            tv_base_tb_left.setVisibility(GONE);
        } else {
            tv_base_tb_left.setText(left);
            ll_base_tb_left.setVisibility(VISIBLE);
        }
        if (resId == 0) {
            iv_base_tb_left.setVisibility(GONE);
        } else {
            iv_base_tb_left.setImageResource(resId);
        }
        if (!Tools.isNull(left) && resId != 0) {
            findViewById(R.id.view_base_tb_left).setVisibility(VISIBLE);
        }
    }


    /**
     * 右侧文字,图片
     * 覆写此方法 ，设置左侧
     *
     * @param icon
     * @param left
     */
    public void setDefaultLayoutLeft(@Nullable Drawable icon, String left) {
        if (Tools.isNull(left)) {
            tv_base_tb_left.setVisibility(GONE);
        } else {
            tv_base_tb_left.setText(left);
            ll_base_tb_left.setVisibility(VISIBLE);
        }
        if (icon == null) {
            iv_base_tb_left.setVisibility(GONE);
        } else {
            iv_base_tb_left.setImageDrawable(icon);
        }
        if (!Tools.isNull(left) && icon != null) {
            findViewById(R.id.view_base_tb_left).setVisibility(VISIBLE);
        }
    }

    /**
     * 右侧文字,图片
     * 覆写此方法 ，设置右侧
     *
     * @param resId
     * @param right
     */
    public void setDefaultLayoutRight(@DrawableRes int resId, String right) {
        if (Tools.isNull(right)) {
            tv_base_tb_right.setVisibility(GONE);
        } else {
            tv_base_tb_right.setText(right);
        }
        if (resId == 0) {
            iv_base_tb_right.setVisibility(GONE);
        } else {
            iv_base_tb_right.setImageResource(resId);
        }
        if (!Tools.isNull(right) && resId != 0) {
            findViewById(R.id.view_base_tb_left).setVisibility(VISIBLE);
        }
    }

    /**
     * 右侧文字,图片
     * 覆写此方法 ，设置右侧
     *
     * @param icon
     * @param right
     */
    public void setDefaultLayoutRight(@Nullable Drawable icon, String right) {
        if (Tools.isNull(right)) {
            tv_base_tb_right.setVisibility(GONE);
        } else {
            tv_base_tb_right.setText(right);
        }
        if (icon == null) {
            iv_base_tb_right.setVisibility(GONE);
        } else {
            iv_base_tb_right.setImageDrawable(icon);
        }
        if (!Tools.isNull(right) && icon != null) {
            findViewById(R.id.view_base_tb_left).setVisibility(VISIBLE);
        }
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


    @Override
    public void onClick(View v) {
        if (baseToolbarListener == null) {
            return;
        }

        if (v.getId() == R.id.tv_base_tb_left) {
            baseToolbarListener.clickLeft(1);
        } else if (v.getId() == R.id.iv_base_tb_left) {
            baseToolbarListener.clickLeft(0);
        } else if (v.getId() == R.id.tv_base_tb_right) {
            baseToolbarListener.clickRight(1);
        } else if (v.getId() == R.id.iv_base_tb_right) {
            baseToolbarListener.clickRight(0);
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
        if (leftChild != null) {
            ll_base_tb_left.removeAllViews();
            ll_base_tb_left.addView(leftChild);
            ll_base_tb_left.setVisibility(VISIBLE);
        }
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
        tb_base_tb.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseToolbar.this.baseToolbarListener.clickLeft(0);
            }
        });
    }

    public interface BaseToolbarListener {

        /**
         * 0 图片 1 文字
         *
         * @param type
         */
        void clickLeft(int type);

        /**
         * 0 图片 1 文字
         *
         * @param type
         */
        void clickRight(int type);

    }


}
