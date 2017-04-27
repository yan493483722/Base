package com.yan.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by YanZi on 2017/4/13.
 * describe：
 * modify:
 * modify date:
 */
public class BaseProgressDialog extends Dialog {


    protected BaseProgressDialog(Context context) {
        super(context, R.style.BaseDialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    protected BaseProgressDialog(Context context, int theme) {
        super(context, theme);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    protected int getMinimumSideOfScreen(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);
            return Math.min(size.x, size.y);
        } else {
            return Math.min(display.getWidth(), display.getHeight());
        }
    }

}
