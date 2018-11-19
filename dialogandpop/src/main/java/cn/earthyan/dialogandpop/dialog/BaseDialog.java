package cn.earthyan.dialogandpop.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.earthyan.dialogandpop.R;

/**
 * Created by YanZi on 2017/4/18.
 * describe：弹窗基类
 * modify:
 * modify date:
 */
public class BaseDialog extends DialogFragment {

    public static abstract class Builder {

        protected float titleTextSize;
        private boolean titleTextBold;
        @ColorInt
        protected int titleTextColor;
        @ColorInt
        protected int contentTextColor;
        protected float contentTextSize;
        protected float btnTextSize;

        protected ColorStateList btnColorStateList;

        //上下文
        protected Activity context;
        //标题
        protected String title;
        //内容
        protected String content;
        //弹窗整体的view
        protected View dialogView;
        //注入的view的父容器
        protected FrameLayout fl_dg_content;
        //标题和内容之间的分割线
        protected View v_dg_divider_10;
        //布局加载器
        protected LayoutInflater mLayoutInflater;

        protected int visibility = View.GONE;
        /**
         * 类型 用于多个弹窗设置同一个listener的回调区分
         */
        protected int tag = -1;
        /**
         * 内容的layout
         */
        @LayoutRes
        protected int contentLayout = 0;

        private int textContentGravity = Gravity.CENTER;

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            this.context = context;
            this.mLayoutInflater = mLayoutInflater;
            titleTextSize = context.getResources().getDimensionPixelSize(R.dimen.font_large);
            titleTextColor = context.getResources().getColor(R.color.font_black);
            contentTextSize = context.getResources().getDimensionPixelSize(R.dimen.font_medium);
            contentTextColor = context.getResources().getColor(R.color.font_black);
            btnTextSize = context.getResources().getDimensionPixelSize(R.dimen.font_medium);
            btnColorStateList = context.getResources().getColorStateList(R.color.selector_text_black_to_primary);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }


        /**
         * 类型 用于多个弹窗设置同一个listener 的回调区分
         */
        public Builder setTag(int tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 类型 用于多个弹窗设置同一个listener 的回调区分
         */
        /**
         * Set the visibility state of this view.
         *
         * @param visibility One of { #View.VISIBLE}, { #INVISIBLE}, or { #GONE}.
         * @attr ref android.R.styleable#View_visibility
         */
        public Builder setCloseVisible(int visibility) {
            this.visibility = visibility;
            return this;
        }

        /**
         * 创建一个dialog
         *
         * @return
         */
        public Dialog create() {
            if (context.isFinishing()) {
                return null;
            }
            final Dialog dialog = new Dialog(context, R.style.base_dg);
            dialogView = mLayoutInflater.inflate(R.layout.dg_base, null);


            fl_dg_content = (FrameLayout) dialogView
                    .findViewById(R.id.fl_dg_content);
            final FrameLayout fl_dg_bottom = (FrameLayout) dialogView
                    .findViewById(R.id.fl_dg_bottom);

            v_dg_divider_10 = dialogView
                    .findViewById(R.id.v_dg_divider_10);

            dialogView
                    .findViewById(R.id.rl_close).setVisibility(visibility);
            dialogView
                    .findViewById(R.id.rl_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            //标题
            final TextView titleTextView = dialogView
                    .findViewById(R.id.tv_dg_title);
            if (TextUtils.isEmpty(title)) {
                titleTextView.setVisibility(View.GONE);
                v_dg_divider_10.setVisibility(View.GONE);
            } else {
                titleTextView.setText(title);
            }
            titleTextView.setTextColor(titleTextColor);
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            if (titleTextBold) {
                titleTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            }
            //按钮
            initBtn(fl_dg_bottom, dialog);
            //内容
            contentLayout = setContentLayout();
            if (contentLayout != 0) {
                fl_dg_content.addView(mLayoutInflater.inflate(contentLayout, null));
                initContent(dialogView);
            }

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
        void initContent(View dialogView) {
            if (!TextUtils.isEmpty(content)) {
                final TextView textView = ((TextView) dialogView.findViewById(R.id.tv_dg_content));
                textView.setGravity(textContentGravity);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentTextSize);
                textView.setTextColor(contentTextColor);
                textView.setText(content);
            } else {
                dialogView.findViewById(R.id.tv_dg_content).setVisibility(View.GONE);
                if (v_dg_divider_10.getVisibility() == View.VISIBLE) {
                    v_dg_divider_10.setVisibility(View.GONE);
                }
            }
        }

        public Builder setContentTextGravity(int textContentGravity) {
            this.textContentGravity = textContentGravity;
            return this;
        }

        /**
         * 实例化按钮 此时已经有了单个按钮和多个按钮 只需要去实例化就行了并且添加点击事件
         *
         * @param fl_dg_bottom 底部的父布局，自己注入新的布局
         */
        abstract void initBtn(FrameLayout fl_dg_bottom, Dialog dialog);

        /**
         * 如果没有的话 默认会给予一个 TextView  R.layout.dg_base_content
         *
         * @return
         */
        @LayoutRes
        abstract int setContentLayout();

        public Builder setTitleTextSize(@DimenRes int titleTextSize) {
            this.titleTextSize = context.getResources().getDimensionPixelSize(titleTextSize);
            return this;
        }


        public Builder setTitleTextColorInt(@ColorInt int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Builder setTitleTextColorRes(@ColorRes int titleTextColor) {
            this.titleTextColor = context.getResources().getColor(titleTextColor);
            return this;
        }

        public Builder setContentTextSize(@DimenRes int contentTextSize) {
            this.contentTextSize = context.getResources().getDimensionPixelSize(contentTextSize);
            return this;
        }

        public Builder setContentTextColorInt(@ColorInt int contentTextColor) {
            this.contentTextColor = contentTextColor;
            return this;
        }

        public Builder setContentTextColorRes(@ColorRes int contentTextColor) {
            this.contentTextColor = context.getResources().getColor(contentTextColor);
            return this;
        }


        public Builder setTitleTextBold(boolean titleTextBold) {
            this.titleTextBold = titleTextBold;
            return this;
        }


        public BaseDialog.Builder setBtnTextSize(@DimenRes int btnTextSize) {
            this.btnTextSize = context.getResources().getDimensionPixelSize(btnTextSize);
            return this;
        }

        public BaseDialog.Builder setBtnColorStateList(@ColorRes int btnColorStateList) {
            this.btnColorStateList = context.getResources().getColorStateList(btnColorStateList);
            return this;
        }

    }

}
