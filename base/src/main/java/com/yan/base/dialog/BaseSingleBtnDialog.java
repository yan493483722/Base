package com.yan.base.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yan.base.R;
import com.yan.base.listener.BaseDialogDoubleBtnClickListener;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2017/4/18.
 * describe：
 * modify:
 * modify date:
 */
public class BaseSingleBtnDialog extends BaseDialog {


    public BaseSingleBtnDialog(Context context) {
        super(context);
    }

    public BaseSingleBtnDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseSingleBtnDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder extends BaseDialog.Builder {

        protected BaseDialogDoubleBtnClickListener baseDialogDoubleBtnClickListener;

        public void setBaseDialogDoubleBtnClickListener(BaseDialogDoubleBtnClickListener baseDialogDoubleBtnClickListener) {
            this.baseDialogDoubleBtnClickListener = baseDialogDoubleBtnClickListener;
        }

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
        }

        @Override
        void initContent(View dialogView) {

        }


        @Override
        void initBtn(View dialogView, final BaseDialog dialog) {
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
                    dialog.dismiss();
                    if (baseDialogDoubleBtnClickListener != null) {
                        baseDialogDoubleBtnClickListener.clickLeftBtn(type);
                    }
                }
            });

            tv_dg_double_right.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (baseDialogDoubleBtnClickListener != null) {
                        baseDialogDoubleBtnClickListener.clickRightBtn(type);
                    }
                }
            });
        }

        @Override
        int setContentView() {
            return 0;
        }


    }
}
