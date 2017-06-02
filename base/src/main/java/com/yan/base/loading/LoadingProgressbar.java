package com.yan.base.loading;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by YanZi on 2017/5/26.
 * describe：
 * modify:
 * modify date:
 */
public class LoadingProgressbar extends View{
    public LoadingProgressbar(Context context) {
        super(context);
    }

    public LoadingProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingProgressbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
