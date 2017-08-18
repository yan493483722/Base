package com.yan.base.uitls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 项目名称：
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/6/21 14:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RecyclerListDiv extends RecyclerView.ItemDecoration {

    /**
     * Orientation vertical 时
     * 绘制水平的分割线
     */
    public static final int HORIZONTAL = 1;

    /**
     * Orientation horizontal 时
     * 绘制垂直的分割线
     */
    public static final int VERTICAL = 2;

    /**
     * 默认2   ====1像素，0.5dp
     */
    private static final int DEFAULT_HEIGHT = 2;
    private Paint mPaint;
    private Drawable mDivider;
    private int mDivHeight;

    private int divType;

    //默认的
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};


    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    @interface  TYPE {
    }

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param divType 分割线类型 HORIZONTAL VERTICAL
     */
    public RecyclerListDiv(Context context, @TYPE int divType) {
        checkArgument(divType);
        mDivHeight = DEFAULT_HEIGHT;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }


    /**
     * 自定义图片分割线
     *
     * @param context
     * @param divType    分割线类型 HORIZONTAL VERTICAL
     * @param drawableId 分割线图片
     */
    public RecyclerListDiv(Context context, @TYPE  int divType, int drawableId) {
        checkArgument(divType);
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDivHeight = mDivider.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     *
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public RecyclerListDiv( @TYPE int divType, int dividerHeight, int dividerColor) {
        checkArgument(divType);
        mDivHeight = dividerHeight < 2 ? DEFAULT_HEIGHT : dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void checkArgument(int divType) {
        if (divType != HORIZONTAL && divType != VERTICAL) {
            throw new IllegalArgumentException("divType must  RecycleListDiv.HORIZONTAL or RecycleListDiv.VERTICAL ");
        }
        this.divType = divType;
    }


    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int total = parent.getAdapter().getItemCount();

        int position = parent.getChildLayoutPosition(view);
        if (divType == HORIZONTAL) {
            int top = position == 0 ? 0 :  mDivHeight ;
            outRect.set(0, top, 0, 0);
        } else {
            int left = position == 0 ? 0 :mDivHeight;
            outRect.set(left, 0, 0, 0);
        }

//        int eachVerticalHeight = (mDivHeight * (total - 1)) / total;
//        int position = parent.getChildLayoutPosition(view);
//        if (divType == HORIZONTAL) {
//            int top = position == 0 ? 0 : (position == total - 1) ? eachVerticalHeight : eachVerticalHeight / 2;
//            int bottom = position == total - 1 ? 0 : position == 0 ? eachVerticalHeight : eachVerticalHeight / 2;
//            outRect.set(0, top, 0, bottom);
//        } else {
//            int left = position == 0 ? 0 : (position == total - 1) ? eachVerticalHeight : eachVerticalHeight / 2;
//            int right = position == total - 1 ? 0 : position == 0 ? eachVerticalHeight : eachVerticalHeight / 2;
//            outRect.set(left, 0, right, 0);
//        }
    }


    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (divType == HORIZONTAL) {
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }

    }

    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize - 1; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDivHeight;
            if (mDivider != null) {
                //画框
                mDivider.setBounds(left, top, right, bottom);
                //画元素
                mDivider.draw(canvas);
            }
            //自定义分割线的大小
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    //绘制纵向 item 分割线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize - 1; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDivHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }


}
