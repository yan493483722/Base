package com.yan.base.listener;

/**
 * Created by YanZi on 2016/11/29.
 * describe：基类单个按钮的点击事件
 * modify person:
 * modify date:
 * modify desc:
 */
public interface BaseDialogSingleBtnClickListener{

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     * @param tag
     */
    void clickBtn(int tag);

}
