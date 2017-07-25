package com.yan.base.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/7/25.
 * describe：
 * modify:
 * modify date:
 */
public class BaseToolbar extends FrameLayout implements View.OnClickListener {

    public static final int STATUS_BAR_TYPE_NORMAL = 1;
    public static final int STATUS_BAR_TYPE_IMG = STATUS_BAR_TYPE_NORMAL+1;
    public static final int STATUS_BAR_TYPE_NO = STATUS_BAR_TYPE_IMG+1;
    public static final int STATUS_BAR_TYPE_FULL = STATUS_BAR_TYPE_NO+1;


    public Toolbar tb_base_tb;
    RelativeLayout rl_base_tb_right;
    ImageView iv_base_tb_right;
    TextView tv_base_tb_right, tv_base_tb_title;

    private BaseToolbarListener baseToolbarListener;

    public BaseToolbar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.toolbar_base, this);
        init(context);
    }

    public void setBaseToolbarListener(BaseToolbarListener baseToolbarListener) {
        this.baseToolbarListener = baseToolbarListener;
    }

    public BaseToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.toolbar_base, this);
        init(context);
    }

    public BaseToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.toolbar_base, this);
        tb_base_tb = (Toolbar) findViewById(R.id.tb_base_tb);
        rl_base_tb_right = (RelativeLayout) findViewById(R.id.rl_base_tb_right);
        iv_base_tb_right = (ImageView) findViewById(R.id.iv_base_tb_right);
        tv_base_tb_right = (TextView) findViewById(R.id.tv_base_tb_right);
        tv_base_tb_title = (TextView) findViewById(R.id.tv_base_tb_title);
        rl_base_tb_right.setOnClickListener(this);
        tb_base_tb.setNavigationIcon(R.mipmap.icon_back);
        tb_base_tb.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseToolbarListener != null) {
                    baseToolbarListener.clickLeft();
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tb_base_tb.setElevation(5);
        }

    }


    public void setBaseToolbarStatusBarType(int type) {
       switch (type){
           case STATUS_BAR_TYPE_NORMAL:
               tb_base_tb.setPopupTheme(R.style.AppTheme);
               break;
           case STATUS_BAR_TYPE_IMG:
               tb_base_tb.setPopupTheme(R.style.AppTheme);
               break;
           case STATUS_BAR_TYPE_NO:
               tb_base_tb.setPopupTheme(R.style.AppTheme);
               break;
           case STATUS_BAR_TYPE_FULL:
               tb_base_tb.setPopupTheme(R.style.AppTheme);
               break;
           default:
               tb_base_tb.setPopupTheme(R.style.AppTheme);
               break;
       }
    }

    public void setTitleText(String title){
        tv_base_tb_title.setText(title);
    }

    public void setRightText(String right){
        tv_base_tb_right.setText(right);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_base_tb_right) {
            if (baseToolbarListener != null) {
                baseToolbarListener.clickRight();
            }
        } else if (v.getId() == R.id.tv_base_tb_title) {
//            if (baseToolbarListener != null) {
//                baseToolbarListener.clickTitle();
//            }
        }
    }


    public interface  BaseToolbarListener {
       void clickLeft();

      void clickRight();
    }

}
