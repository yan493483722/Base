package cn.earthyan.dialogandpop.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.earthyan.dialogandpop.R;
import cn.earthyan.dialogandpop.listener.BaseDialogSingleBtnClickListener;

/**
 * Created by YanZi on 2017/4/18.
 * describe：底部一个按钮的弹窗
 * modify:
 * modify date:
 */
public class BaseSingleBtnDialog extends BaseDialog {

    public static class Builder extends BaseDialog.Builder {

        protected String btnText;

        protected BaseDialogSingleBtnClickListener baseDialogSingleBtnClickListener;

        public Builder setBaseDialogSingleBtnClickListener(BaseDialogSingleBtnClickListener baseDialogSingleBtnClickListener) {
            this.baseDialogSingleBtnClickListener = baseDialogSingleBtnClickListener;
            return this;
        }

        public Builder setBtnText(String btnText) {
            this.btnText = btnText;
            return this;
        }

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
        }

        @Override
        void initBtn(FrameLayout fl_dg_bottom, final Dialog dialog) {
            final View view = LayoutInflater.from(context).inflate(R.layout.dg_base_bottom_btn_single, fl_dg_bottom, false);
            fl_dg_bottom.removeAllViews();
            fl_dg_bottom.addView(view);

            // 设置按钮
            TextView tv_dg_single = (TextView) view
                    .findViewById(R.id.tv_dg_single);

            if (!TextUtils.isEmpty(btnText)) {
                tv_dg_single.setText(btnText);
            }
            tv_dg_single.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (baseDialogSingleBtnClickListener != null) {
                        baseDialogSingleBtnClickListener.clickBtn(tag);
                    }
                }
            });

            tv_dg_single.setTextColor(btnColorStateList);
            tv_dg_single.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_content;
        }


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

        @Override
        public Builder setBtnTextSize(int btnTextSize) {
            super.setBtnTextSize(btnTextSize);
            return this;
        }

        @Override
        public Builder setBtnColorStateList(int btnColorStateList) {
            super.setBtnColorStateList(btnColorStateList);
            return this;
        }
    }

}
