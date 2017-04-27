package com.yan.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yan.base.BaseAty;
import com.yan.base.R;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2017/4/18.
 * describe：
 * modify:
 * modify date:
 */
public class BaseDialog extends Dialog {

    protected BaseAty mBaseAty;

    protected LayoutInflater mLayoutInflater;

    /**
     * dialog
     */
    protected Dialog dialog;
    /**
     * 填充到dialog的视图
     */
    protected View dialogView;
    /**
     * dialog的标题
     */
    protected TextView tv_dg_title;
    /**
     * dialog的内容，可以增加不同的
     */
    protected FrameLayout fl_dg_content;
    /**
     * 标题和内容之间的间距
     */
    protected View v_dg_divider_10;

    /**
     * 类型
     */
    protected int type;


    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public static class Builder {
        //上下文
        private Activity context;
        //标题
        private String title;
        //内容
        private String content;
        //左侧按钮文字
        private String leftBtnString;
        //右侧按钮文字
        private String rightBtnString;
        //弹窗整体的view
        private View dialogView;
        //注入的view的父容器
        private FrameLayout fl_dg_content;
        //标题和内容之间的分割线
        private View v_dg_divider_10;
        //布局加载器
        private LayoutInflater mLayoutInflater;

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            this.context = context;
            this.mLayoutInflater = mLayoutInflater;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setLeftBtnString(String leftBtnString) {
            this.leftBtnString = leftBtnString;
            return this;
        }

        public Builder setRightBtnString(String rightBtnString) {
            this.rightBtnString = rightBtnString;
            return this;
        }


        /**
         * @return
         */
        public BaseDialog create() {
            if (context.isFinishing()) {
                return null;
            }
            BaseDialog dialog = new BaseDialog(context, R.style.base_aty_dialog);
            dialogView = mLayoutInflater.inflate(R.layout.dg_base, null);
            //标题
            ((TextView) dialogView
                    .findViewById(R.id.tv_dg_title)).setText(title);
            fl_dg_content = (FrameLayout) dialogView
                    .findViewById(R.id.fl_dg_content);
            v_dg_divider_10 = dialogView
                    .findViewById(R.id.v_dg_divider_10);
            setContentView();
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent);
            return dialog;
        }

        void setContentView() {

        }
    }


    protected View initBaseDialog() {
        if (mBaseAty.isFinishing()) {
            return null;
        }
        // 初始化控件


        return dialogView;
    }


    protected void initNormalTitleContent(String title, String content) {
        // 标题不为空 内容不为空
        if (!Tools.isNull(title) && !Tools.isNull(content)) {
            tv_dg_title.setText(title);
            View contentView = mLayoutInflater.inflate(R.layout.dg_base_content, fl_dg_content);
            fl_dg_content.addView(contentView);
            TextView tv_dg_content = (TextView) contentView.findViewById(R.id.tv_dg_content);
            tv_dg_content.setText(content);
        } else {
            if (!Tools.isNull(title) && Tools.isNull(content)) {
                tv_dg_title.setText(title);
                v_dg_divider_10.setVisibility(View.GONE);
            } else {
//                View contentView = mLayoutInflater.inflate(R.layout.dg_base_content, fl_dg_content);
//                fl_dg_content.addView(contentView);
//                TextView tv_dg_content = (TextView) contentView.findViewById(R.id.tv_dg_content);
//                tv_dg_title.setVisibility(View.GONE);
//                tv_dg_content.setText(content);
                tv_dg_title.setText(content);
                v_dg_divider_10.setVisibility(View.GONE);
            }
        }
    }


}
