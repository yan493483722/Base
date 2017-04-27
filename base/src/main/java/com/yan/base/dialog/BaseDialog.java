package com.yan.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/4/18.
 * describe：
 * modify:
 * modify date:
 */
public class BaseDialog extends Dialog {


    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static abstract class Builder<T> {
        //上下文
        protected Activity context;
        //标题
        protected String title;
        //内容
        protected String content;
        //左侧按钮文字
        protected String leftBtnString;
        //右侧按钮文字
        protected String rightBtnString;
        //弹窗整体的view
        protected View dialogView;
        //注入的view的父容器
        protected FrameLayout fl_dg_content;
        //标题和内容之间的分割线
        protected View v_dg_divider_10;
        //布局加载器
        protected LayoutInflater mLayoutInflater;

        /**
         * 类型 用于多个弹窗设置同一个listener的回调区分
         */
        protected int type;
        /**
         * 内容的layout
         */
        @LayoutRes
        protected int contentLayout = R.layout.dg_base_content;

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
         * 类型 用于多个弹窗设置同一个listener的回调区分
         */
        public void setType(int type) {
            this.type = type;
        }

        /**
         * 创建一个dialog
         *
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
            contentLayout = setContentView();
            fl_dg_content.addView(mLayoutInflater.inflate(contentLayout, fl_dg_content));

            initBtn(dialogView,dialog);
            initContent(dialogView);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent);
            dialog.setContentView(dialogView);
            return dialog;
        }

        /**
         * 实例化内部的内容
         *
         * @param dialogView 用于findViewById
         */
        abstract void initContent(View dialogView);

        /**
         * 实例化按钮 此时已经有了单个按钮和多个按钮 只需要去实例化就行了并且添加点击事件
         *
         * @param dialogView 用于findViewById
         */
        abstract void initBtn(View dialogView,BaseDialog dialog);

        /**
         * 如果没有的话 默认会给予一个 TextView  R.layout.dg_base_content
         *
         * @return
         */
        @LayoutRes
        abstract int setContentView();


    }


}
