package com.yan.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 项目名称：cailaia
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/6/21 14:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RecyclerGridDiv extends RecyclerView.ItemDecoration {


    public static final int GRID = 0;

    /**
     * Orientation vertical 时 TODO 这部分还有待完善，因为用到这部分需求的比较少
     * 绘制水平的分割线
     */
    public static final int STAGGERED_GRID_HORIZONTAL = 1;

    /**
     * Orientation horizontal 时
     * 绘制垂直的分割线
     */
    public static final int STAGGERED_GRID_VERTICAL = 2;


    /**
     * div default height
     */
    private static final int DEFAULT_HEIGHT = 2;

    private Paint mPaintHorizontal;
    private Paint mPaintVertical;

    private Drawable mDividerHorizontal;
    private Drawable mDividerVertical;
    private int mHorizontalHeight;
    private int mVerticalHeight;

    private int mDivType;

    private int spanCount;

    private boolean includeEdge;

    //默认的
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param divType 分割线类型
     */
    public RecyclerGridDiv(Context context, int divType) {
        checkArgument(divType);
        mVerticalHeight = mHorizontalHeight = DEFAULT_HEIGHT;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDividerVertical = mDividerHorizontal = a.getDrawable(0);
        a.recycle();
    }

    /**
     * 自定义图片分割线
     *
     * @param context
     * @param divType    列表方向
     * @param drawableId 分割线图片
     */
    public RecyclerGridDiv(Context context, int divType, int drawableId) {
        checkArgument(divType);
        mDividerVertical = mDividerHorizontal = ContextCompat.getDrawable(context, drawableId);
        mVerticalHeight = mHorizontalHeight = mDividerHorizontal.getIntrinsicHeight();
    }

    /**
     * 自定义图片分割线
     *
     * @param context
     * @param divType              列表方向
     * @param drawableHorizontalId 水平分割线图片
     * @param drawableVerticalId   垂直分割线图片
     */
    public RecyclerGridDiv(Context context, int divType, int drawableHorizontalId, int drawableVerticalId) {
        checkArgument(divType);
        mDividerHorizontal = ContextCompat.getDrawable(context, drawableHorizontalId);
        mHorizontalHeight = mDividerHorizontal.getIntrinsicHeight();
        mDividerVertical = ContextCompat.getDrawable(context, drawableVerticalId);
        mVerticalHeight = mDividerVertical.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     *
     * @param
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public RecyclerGridDiv(int divType, int dividerHeight, int dividerColor) {
        checkArgument(divType);
        mVerticalHeight = mHorizontalHeight = dividerHeight;
        mPaintHorizontal = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintHorizontal.setColor(dividerColor);
        mPaintHorizontal.setStyle(Paint.Style.FILL);
        mPaintVertical = mPaintHorizontal;
    }


    public RecyclerGridDiv(int divType, int dividerHorizontal, int dividerHorizontalColor, int dividerVertical, int dividerVerticalColor) {
        checkArgument(divType);
        mHorizontalHeight = dividerHorizontal;

        mVerticalHeight = dividerVertical;

        mPaintHorizontal = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintHorizontal.setColor(dividerHorizontalColor);
        mPaintHorizontal.setStyle(Paint.Style.FILL);

        mPaintVertical = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintVertical.setColor(dividerVerticalColor);
        mPaintVertical.setStyle(Paint.Style.FILL);
    }

    private void checkArgument(int divType) {
        if (divType != GRID && divType != STAGGERED_GRID_HORIZONTAL && divType != STAGGERED_GRID_VERTICAL) {
            throw new IllegalArgumentException("divType must  RecyclerGridDiv.GRID or" +
                    " RecyclerGridDiv.STAGGERED_GRID_HORIZONTAL  or RecyclerGridDiv.STAGGERED_GRID_VERTICAL");
        }
        this.mDivType = divType;
    }


    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        spanCount = getSpanCount(parent);
        int eachVerticalHeight = (mVerticalHeight * (spanCount - 1)) / spanCount;
        int position = parent.getChildLayoutPosition(view);
        int column = position % spanCount;
        int total = parent.getAdapter().getItemCount();
        boolean isLastSpan = isLastSpan(position, total);
        boolean isLastLine = isLastLine(position, total);
        int left = column == 0 ? 0 : (column == spanCount - 1) ? eachVerticalHeight : eachVerticalHeight / 2;
        int top = position >= spanCount ? mHorizontalHeight / 2 : 0;
        int right = isLastSpan ? 0 : column == 0 ? eachVerticalHeight : eachVerticalHeight / 2;
        int bottom = isLastLine ? 0 : mHorizontalHeight / 2;
        if((column!=spanCount-1)&&position == (total - 1)){
             right =column == 0 ? eachVerticalHeight : eachVerticalHeight / 2;
        }
        outRect.set(left, top, right, bottom);

    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount;
        if (mDivType == GRID) {
            spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        } else {
            spanCount = ((StaggeredGridLayoutManager) parent.getLayoutManager())
                    .getSpanCount();
        }
        return spanCount;
    }

    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawGrid(c, parent);

    }

    //绘制纵向 item 分割线
    private void drawGrid(Canvas canvas, RecyclerView parent) {
        drawGridHorizontal(canvas, parent);
        drawGridVertical(canvas, parent);
    }


    public void drawGridHorizontal(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isLastLine(i, childCount)) {//最后一行不绘制横向的线条
                continue;
            }

            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mVerticalHeight;
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mHorizontalHeight;
            if (mDividerHorizontal != null) {
                //画框
                mDividerHorizontal.setBounds(left, top, right, bottom);
                //画元素
                mDividerHorizontal.draw(canvas);
            }
            //自定义分割线的大小
            if (mPaintHorizontal != null) {
                canvas.drawRect(left, top, right, bottom, mPaintHorizontal);
            }
        }
    }

    public void drawGridVertical(Canvas canvas, RecyclerView parent) {
        //每个实际item将会减少的将会偏移量
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isLastSpan(i, childCount)) {//最后一列不绘制竖线
                continue;
            }
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mVerticalHeight;

            if (mDividerVertical != null) {
                //画框
                mDividerVertical.setBounds(left, top, right, bottom);
                //画元素
                mDividerVertical.draw(canvas);
            }
            //自定义分割线的大小
            if (mPaintVertical != null) {
                canvas.drawRect(left, top, right, bottom, mPaintVertical);
            }
        }
    }

    /**
     * 是不是最后一列
     *
     * @param position
     * @param childCount
     * @return
     */

    private boolean isLastSpan(int position, int childCount) {
        if (mDivType == GRID) {
            // 如果是最后一列，或者是最后一排的最后一行 不需要绘制右边
            if ((position + 1) % spanCount == 0 || position == (childCount - 1)) {
                return true;
            }
        } else if (mDivType == STAGGERED_GRID_HORIZONTAL) {//排列方式发生改变了计算方式发生改变
            // 恰好是最后一行
            if ((0 == childCount % spanCount) && position >= childCount - spanCount) {
                return true;
            }
            childCount = childCount - childCount % spanCount;
            if (position >= childCount) {
                // 如果是最后一行，则不需要绘制底部
                return true;
            }
        } else {
            // 如果是最后一列，或者是最后一排的最后一行 不需要绘制右边
            if ((position + 1) % spanCount == 0 || position == childCount - 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isLastLine(int position, int childCount) {

        if (mDivType == GRID) {
            // 恰好是最后一行
            if ((0 == childCount % spanCount) && position >= childCount - spanCount) {
                return true;
            }
            // 比最后一行要多
            childCount = childCount - childCount % spanCount;
            if (position >= childCount) {
                // 如果是最后一行，则不需要绘制底部
                return true;
            }
        } else if (mDivType == STAGGERED_GRID_HORIZONTAL) {
            // 如果是最后一行，则不需要绘制底部
            if ((position + 1) % spanCount == 0 || position == childCount - 1) {
                return true;
            }
        } else {
            // 恰好是最后一行
            if ((0 == childCount % spanCount) && position >= childCount - spanCount) {
                return true;
            }
            childCount = childCount - childCount % spanCount;
            if (position >= childCount) {
                // 如果是最后一行，则不需要绘制底部
                return true;
            }
        }


        return false;
    }

}

