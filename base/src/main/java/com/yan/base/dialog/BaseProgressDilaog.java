package com.yan.base.dialog;

import android.content.Context;

/**
 * Created by YanZi on 2017/8/21.
 * describe：
 * modify:
 * modify date:
 */
public class BaseProgressDilaog extends BaseDialog {

    protected BaseProgressDilaog(Context context) {
        super(context);
    }

    protected BaseProgressDilaog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseProgressDilaog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }



}
