package cn.earthyan.dialogandpop.listener;

/**
 * Created by YanZi on 2016/11/29.
 * describe：基类左右两个按钮的点击事件
 * modify person:
 * modify date:
 * modify desc:
 */
public interface BaseDialogInputDoubleBtnClickListener {

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     *
     * @param tag
     * @param text 输入的文字
     */
    void clickLeftBtn(int tag, String text);

    /**
     * 弹窗类型 多个弹窗时候使用此type来区分
     *
     * @param tag
     * @param text 输入的文字
     */
    void clickRightBtn(int tag, String text);


}
