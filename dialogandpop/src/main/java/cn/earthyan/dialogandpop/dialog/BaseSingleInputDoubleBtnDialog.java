package cn.earthyan.dialogandpop.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.earthyan.dialogandpop.R;
import cn.earthyan.dialogandpop.listener.BaseDialogDoubleBtnClickListener;
import cn.earthyan.dialogandpop.listener.BaseDialogInputDoubleBtnClickListener;

/**
 * Created by YanZi on 2017/4/18.
 * describe：前面字数限定，弹窗---用途完善用户名
 * modify:
 * modify date:
 */
public class BaseSingleInputDoubleBtnDialog extends BaseDialog {

    public static class Builder extends BaseDoubleBtnDialog.Builder {

        protected EditText et_dg_input_content;
        protected TextView tv_dg_input_end;

        protected BaseDialogInputDoubleBtnClickListener baseDialogInputDoubleBtnClickListener;

        public Builder setBaseDialogInputDoubleBtnClickListener(BaseDialogInputDoubleBtnClickListener baseDialogInputDoubleBtnClickListener) {
            this.baseDialogInputDoubleBtnClickListener = baseDialogInputDoubleBtnClickListener;
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

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
        }

        @Override
        void initContent(View dialogView) {
            et_dg_input_content = (EditText) dialogView.findViewById(R.id.et_dg_input_content);
            tv_dg_input_end = (TextView) dialogView.findViewById(R.id.tv_dg_input_end);
            if (!TextUtils.isEmpty(content)) {
                tv_dg_input_end.setText(content);
            } else {
                tv_dg_input_end.setVisibility(View.GONE);
            }
        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_input_one_content;
        }


        @Override
        public Builder setBaseDialogDoubleBtnClickListener(BaseDialogDoubleBtnClickListener baseDialogDoubleBtnClickListener) {
            super.setBaseDialogDoubleBtnClickListener(baseDialogDoubleBtnClickListener);
            return this;
        }


        @Override
        public Builder setLeftBtnColorStateList(int btnColorStateList) {
            super.setLeftBtnColorStateList(btnColorStateList);
            return this;
        }

        @Override
        public Builder setRightBtnColorStateList(int btnColorStateList) {
            super.setRightBtnColorStateList(btnColorStateList);
            return this;
        }

        @Override
        public Builder setLeftBtnTextSize(int btnTextSize) {
            super.setLeftBtnTextSize(btnTextSize);
            return this;
        }

        @Override
        public Builder setRightBtnTextSize(int btnTextSize) {
            super.setRightBtnTextSize(btnTextSize);
            return this;
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
