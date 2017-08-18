package com.yan.base.listener;

/**
 * Created by YanZi on 2016/11/29.
 * describe：基类左右两个按钮的点击事件
 * modify person:
 * modify date:
 * modify desc:
 */
public interface BaseDialogDoubleBtnClickListener {

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     * @param tag
     */
    void clickLeftBtn(int tag);

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     * @param type
     */
    void clickRightBtn(int tag);


}
