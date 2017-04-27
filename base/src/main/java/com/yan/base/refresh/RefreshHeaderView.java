package com.yan.base.refresh;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.yan.base.R;


/**
 * 项目名称：Base
 * 类描述：
 * 创建人：YanZi
 * 创建时间：2016/6/14 10:02
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {

    private Resources mResources;

    private OnScrollChangeListener onScrollChangeListener;

    public RefreshHeaderView(Context context) {
        super(context);
        mResources = context.getResources();
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mResources = context.getResources();
    }

    @Override
    public void onRefresh() {
        setText(mResources.getString(R.string.refreshing));
    }

    @Override
    public void onPrepare() {
        setText("");
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onPrepare();
        }
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                setText(mResources.getString(R.string.release_to_refresh));
            } else {
                setText(mResources.getString(R.string.down_to_refresh));
            }
        } else {
            setText(mResources.getString(R.string.refreshing));
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        setText(mResources.getString(R.string.refresh_complete));
    }

    @Override
    public void onReset() {
        setText("");
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onReset();
        }

    }


    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public interface OnScrollChangeListener {

        void onPrepare();

        void onReset();
    }
}
