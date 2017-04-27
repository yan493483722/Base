package com.yan.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yan.base.R;
import com.yan.base.listener.BaseDialogInputDoubleBtnClickListener;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2017/4/18.
 * describe：
 * modify:
 * modify date:
 */
public class BaseInputDoubleBtnDialog extends BaseDialog {


    public BaseInputDoubleBtnDialog(Context context) {
        super(context);
    }

    public BaseInputDoubleBtnDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseInputDoubleBtnDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder extends BaseDialog.Builder {
        //左侧按钮文字
        protected String leftBtnString;
        //右侧按钮文字
        protected String rightBtnString;

        private EditText et_dg_input_content;
        private TextView tv_dg_input_end;

        protected BaseDialogInputDoubleBtnClickListener baseDialogInputDoubleBtnClickListener;

        public Builder setBaseDialogInputDoubleBtnClickListener(BaseDialogInputDoubleBtnClickListener baseDialogInputDoubleBtnClickListener) {
            this.baseDialogInputDoubleBtnClickListener = baseDialogInputDoubleBtnClickListener;
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
            }
        }


        @Override
        void initBtn(View dialogView, final Dialog dialog) {
            TextView tv_dg_single = (TextView) dialogView
                    .findViewById(R.id.tv_dg_single);
            TextView tv_dg_double_left = (TextView) dialogView
                    .findViewById(R.id.tv_dg_double_left);
            TextView tv_dg_double_right = (TextView) dialogView
                    .findViewById(R.id.tv_dg_double_right);

            tv_dg_single.setVisibility(View.GONE);

            if (!Tools.isNull(leftBtnString)) {
                tv_dg_double_left.setText(leftBtnString);
            }
            if (!Tools.isNull(rightBtnString)) {
                tv_dg_double_right.setText(rightBtnString);
            }
            tv_dg_double_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (baseDialogInputDoubleBtnClickListener != null) {
                        baseDialogInputDoubleBtnClickListener.clickLeftBtn(type, et_dg_input_content.getText().toString());
                    }
                    dialog.dismiss();
                }
            });
            tv_dg_double_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (baseDialogInputDoubleBtnClickListener != null) {
                        baseDialogInputDoubleBtnClickListener.clickRightBtn(type, et_dg_input_content.getText().toString());
                    }
                    dialog.dismiss();
                }
            });

        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_input_one_content;
        }


    }
}
