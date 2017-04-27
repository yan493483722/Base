package com.yan.base.refresh;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.yan.base.R;

/**
 * 项目名称：Base
 * 类描述：
 * 创建人：YanZi
 * 创建时间：2016/6/14 10:04
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoadMoreFooterView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {

    private Resources mResources;

    public LoadMoreFooterView(Context context) {
        super(context);
        mResources = context.getResources();
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mResources = context.getResources();
    }

    @Override
    public void onLoadMore() {
        setText(mResources.getString(R.string.refreshing));
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText(mResources.getString(R.string.refreshing));
            } else {
                setText(mResources.getString(R.string.pull_to_load));
            }
        } else {
            setText(mResources.getString(R.string.refreshing));
        }
    }

    @Override
    public void onRelease() {
        setText(mResources.getString(R.string.refreshing));
    }

    @Override
    public void onComplete() {
        setText(mResources.getString(R.string.load_complete));
    }

    @Override
    public void onReset() {
        setText("");
    }
}