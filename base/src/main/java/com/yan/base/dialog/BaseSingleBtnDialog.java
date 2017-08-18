package com.yan.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yan.base.R;
import com.yan.base.listener.BaseDialogSingleBtnClickListener;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2017/4/18.
 * describe：底部一个按钮的弹窗
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

        protected String btnText;


        protected BaseDialogSingleBtnClickListener baseDialogSingleBtnClickListener;

        public Builder setBaseDialogSingleBtnClickListener(BaseDialogSingleBtnClickListener baseDialogSingleBtnClickListener) {
            this.baseDialogSingleBtnClickListener = baseDialogSingleBtnClickListener;
            return this;
        }

        public void setBtnText(String btnText) {
            this.btnText = btnText;
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

            if (!Tools.isNull(btnText)) {
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
        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_content;
        }


    }
}
