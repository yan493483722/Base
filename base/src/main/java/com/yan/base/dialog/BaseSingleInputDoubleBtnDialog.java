package com.yan.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yan.base.R;
import com.yan.base.listener.BaseDialogInputDoubleBtnClickListener;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2017/4/18.
 * describe：前面字数限定，弹窗---用途完善用户名
 * modify:
 * modify date:
 */
public class BaseSingleInputDoubleBtnDialog extends BaseDialog {


    public BaseSingleInputDoubleBtnDialog(Context context) {
        super(context);
    }

    public BaseSingleInputDoubleBtnDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseSingleInputDoubleBtnDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

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
            if (!Tools.isNull(content)) {
                tv_dg_input_end.setText(content);
            } else {
                tv_dg_input_end.setVisibility(View.GONE);
            }
        }


        @Override
        int setContentLayout() {
            return R.layout.dg_base_input_one_content;
        }


    }
}
