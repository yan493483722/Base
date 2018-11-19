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

            tv_dg_double_left.setTextColor(btnColorStateList);
            tv_dg_double_left.setTextSize(TypedValue.COMPLEX_UNIT_PX,btnTextSize);
            tv_dg_double_right.setTextColor(btnColorStateList);
            tv_dg_double_right.setTextSize(TypedValue.COMPLEX_UNIT_PX,btnTextSize);
        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_content;
        }

    }

}
