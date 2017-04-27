package com.yan.base.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/4/18.
 * describe：
 * modify:
 * modify date:
 */
public class BaseInputDoubleBtnDialog extends BaseDialog {

    private static final int MAX_LENGTH = 100;

    public BaseInputDoubleBtnDialog(Context context) {
        super(context);
    }

    public BaseInputDoubleBtnDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseInputDoubleBtnDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder extends BaseSingleInputDoubleBtnDialog.Builder {

        private String textHint;

        private int maxLength = 0;

        private LinearLayout ll_dg_base_input;

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
        }

        public Builder setTextHint(String textHint) {
            this.textHint = textHint;
            return this;
        }

        public Builder setMaxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        @Override
        void initContent(View dialogView) {
            super.initContent(dialogView);
            ll_dg_base_input = (LinearLayout) dialogView.findViewById(R.id.ll_dg_base_input);
            ll_dg_base_input.setOrientation(LinearLayout.VERTICAL);
            et_dg_input_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            if (maxLength == 0) {
                maxLength = MAX_LENGTH;
            }
            et_dg_input_content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
            et_dg_input_content.setHint(textHint);
            et_dg_input_content.setGravity(Gravity.LEFT);
            et_dg_input_content.setBackgroundDrawable(new EditText(context)
                    .getBackground());
        }


    }
}
