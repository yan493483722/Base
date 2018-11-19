package cn.earthyan.dialogandpop.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.earthyan.dialogandpop.R;
import cn.earthyan.dialogandpop.listener.BaseDialogDoubleBtnClickListener;

/**
 * Created by YanZi on 2017/4/18.
 * describe：两个按钮的弹窗，中间包含文字
 * modify:
 * modify date:
 */
public class BaseDoubleBtnDialog extends BaseDialog {

    public static class Builder extends BaseDialog.Builder {
        //左侧按钮文字
        protected String leftBtnString;
        //右侧按钮文字
        protected String rightBtnString;
        //左侧按钮文字大小
        protected float leftBtnTextSize;
        //右侧按钮文字大小
        protected float rightBtnTextSize;
        //左侧按钮颜色
        protected ColorStateList leftColorStateList;
        //右侧按钮颜色
        protected ColorStateList rightColorStateList;

        //回调
        protected BaseDialogDoubleBtnClickListener baseDialogDoubleBtnClickListener;


        public Builder setLeftBtnString(String leftBtnString) {
            this.leftBtnString = leftBtnString;
            return this;
        }

        public Builder setRightBtnString(String rightBtnString) {
            this.rightBtnString = rightBtnString;
            return this;
        }

        public Builder setBaseDialogDoubleBtnClickListener(BaseDialogDoubleBtnClickListener baseDialogDoubleBtnClickListener) {
            this.baseDialogDoubleBtnClickListener = baseDialogDoubleBtnClickListener;
            return this;
        }

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
            rightColorStateList = leftColorStateList = btnColorStateList;
            rightBtnTextSize = leftBtnTextSize = btnTextSize;
        }

        @Override
        void initBtn(FrameLayout fl_dg_bottom, final Dialog dialog) {
            final View view = LayoutInflater.from(context).inflate(R.layout.dg_base_bottom_btn_double, fl_dg_bottom, false);
            fl_dg_bottom.removeAllViews();
            fl_dg_bottom.addView(view);

            final TextView tv_dg_double_left = (TextView) view
                    .findViewById(R.id.tv_dg_double_left);
            final TextView tv_dg_double_right = (TextView) view
                    .findViewById(R.id.tv_dg_double_right);

            if (!TextUtils.isEmpty(leftBtnString)) {
                tv_dg_double_left.setText(leftBtnString);
            }
            if (!TextUtils.isEmpty(rightBtnString)) {
                tv_dg_double_right.setText(rightBtnString);
            }
            tv_dg_double_left.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (baseDialogDoubleBtnClickListener != null) {
                        baseDialogDoubleBtnClickListener.clickLeftBtn(tag);
                    }
                }
            });

            tv_dg_double_right.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (baseDialogDoubleBtnClickListener != null) {
                        baseDialogDoubleBtnClickListener.clickRightBtn(tag);
                    }
                }
            });

            tv_dg_double_left.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftBtnTextSize);
            tv_dg_double_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightBtnTextSize);
            tv_dg_double_left.setTextColor(leftColorStateList);
            tv_dg_double_right.setTextColor(rightColorStateList);
        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_content;
        }

        @Override
        public Builder setBtnTextSize(int btnTextSize) {
            super.setBtnTextSize(btnTextSize);
            rightBtnTextSize = leftBtnTextSize = this.btnTextSize;
            return this;
        }

        @Override
        public Builder setBtnColorStateList(int btnColorStateList) {
            super.setBtnColorStateList(btnColorStateList);
            rightColorStateList = leftColorStateList = this.btnColorStateList;
            return this;
        }

        public Builder setLeftBtnColorStateList(@ColorRes int btnColorStateList) {
            this.leftColorStateList = context.getResources().getColorStateList(btnColorStateList);
            return this;
        }

        public Builder setRightBtnColorStateList(@ColorRes int btnColorStateList) {
            this.rightColorStateList = context.getResources().getColorStateList(btnColorStateList);
            return this;
        }


        public Builder setLeftBtnTextSize(@DimenRes int btnTextSize) {
            this.leftBtnTextSize = context.getResources().getDimensionPixelSize(btnTextSize);
            return this;
        }


        public Builder setRightBtnTextSize(@DimenRes int btnTextSize) {
            this.rightBtnTextSize = context.getResources().getDimensionPixelSize(btnTextSize);
            return this;
        }

        // 重写父类的方法 返回子类的实例


        @Override
        public Builder setTitle(String title) {
            super.setTitle(title);
            return this;
        }

        @Override
        public Builder setContent(String content) {
            super.setContent(content);
            return this;
        }

        @Override
        public Builder setTag(int tag) {
            super.setTag(tag);
            return this;
        }

        @Override
        public Builder setCloseVisible(int visibility) {
            super.setCloseVisible(visibility);
            return this;
        }


        @Override
        public Builder setContentTextGravity(int textContentGravity) {
            super.setContentTextGravity(textContentGravity);
            return this;
        }

        @Override
        public Builder setTitleTextSize(int titleTextSize) {
            super.setTitleTextSize(titleTextSize);
            return this;
        }

        @Override
        public Builder setTitleTextColorInt(int titleTextColor) {
            super.setTitleTextColorInt(titleTextColor);
            return this;
        }

        @Override
        public Builder setTitleTextColorRes(int titleTextColor) {
            super.setTitleTextColorRes(titleTextColor);
            return this;
        }

        @Override
        public Builder setContentTextSize(int contentTextSize) {
            super.setContentTextSize(contentTextSize);
            return this;
        }

        @Override
        public Builder setContentTextColorInt(int contentTextColor) {
            super.setContentTextColorInt(contentTextColor);
            return this;
        }

        @Override
        public Builder setContentTextColorRes(int contentTextColor) {
            super.setContentTextColorRes(contentTextColor);
            return this;
        }

        @Override
        public Builder setTitleTextBold(boolean titleTextBold) {
            super.setTitleTextBold(titleTextBold);
            return this;
        }


    }

}
