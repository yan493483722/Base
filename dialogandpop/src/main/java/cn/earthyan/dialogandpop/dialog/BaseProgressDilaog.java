package cn.earthyan.dialogandpop.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by YanZi on 2017/8/21.
 * describe：
 * modify:
 * modify date:
 */
public class BaseProgressDilaog extends BaseDialog {

    class Builder extends BaseDialog.Builder {

        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
        }

        @Override
        void initBtn(FrameLayout fl_dg_bottom, Dialog dialog) {

        }

        @Override
        int setContentLayout() {
            return 0;
        }
    }


}
