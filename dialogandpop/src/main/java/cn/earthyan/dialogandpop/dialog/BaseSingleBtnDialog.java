package cn.earthyan.dialogandpop.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
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
        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_content;
        }

    }

}
