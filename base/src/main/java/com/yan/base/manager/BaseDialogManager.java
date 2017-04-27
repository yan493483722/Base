package com.yan.base.manager;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yan.base.BaseAty;
import com.yan.base.R;
import com.yan.base.listener.BaseDialogDoubleBtnClickListener;
import com.yan.base.listener.BaseDialogSingleBtnClickListener;
import com.yan.base.uitls.Tools;

/**
 * Created by YanZi on 2017/2/22.
 * describe：
 * modify:
 * modify date:
 */
public class BaseDialogManager {


    private BaseAty mBaseAty;

    private LayoutInflater mLayoutInflater;

    private BaseDialogSingleBtnClickListener singleBtnClickListener;
    private BaseDialogDoubleBtnClickListener dialogDoubleBtnClickListener;

    private Dialog dialog;

    public BaseDialogManager(BaseAty mBaseAty, LayoutInflater mLayoutInflater) {
        this.mBaseAty = mBaseAty;
        this.mLayoutInflater = mLayoutInflater;
    }


    public void setBaseDialogSingleBtnClickListener(BaseDialogSingleBtnClickListener singleBtnClickListener) {
        this.singleBtnClickListener = singleBtnClickListener;
    }


    public void setBaseDialogDoubleBtnClickListener(BaseDialogDoubleBtnClickListener dialogDoubleBtnClickListener) {
        this.dialogDoubleBtnClickListener = dialogDoubleBtnClickListener;
    }

    /**
     * @param title   对话框标题
     * @param content 内容
     * @return void 返回类型
     * @Title: showDialogSingleBtn
     * @Description: 显示提示对话框，带一个确认按钮
     * @author yanzi
     */
    public void showDialogSingleBtn(String title, String content, int type) {
        showDialogSingleBtn(title, content, null, type);
    }

    /**
     * @param title   对话框标题
     * @param content 内容
     * @return void 返回类型
     * @Title: showDialogSingleBtn
     * @Description: 显示提示对话框，带一个确认按钮
     * @author yanzi
     */
    public void showDialogDoubleBtn(String title, String content, int type) {
        showDialogDoubleBtn(title, content, null, null, type);
    }

    /**
     * @param title   对话框标题
     * @param content 内容
     * @param btnText 按钮文字 为空时传null 或"" 默认为确定
     * @return void 返回类型
     * @Title: showDialogSingleBtn
     * @Description: 显示提示对话框，带一个确认按钮
     * @author yanzi
     */
    public void showDialogSingleBtn(String title, String content, String btnText, final int type) {

        View contentView = initBaseDialog(title, content);
        // 设置按钮
        TextView tv_dg_single = (TextView) contentView
                .findViewById(R.id.tv_dg_single);
        LinearLayout ll_dg_double_btn = (LinearLayout) contentView
                .findViewById(R.id.ll_dg_double_btn);

        ll_dg_double_btn.setVisibility(View.GONE);
        if (!Tools.isNull(btnText)) {
            tv_dg_single.setText(btnText);
        }
        tv_dg_single.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (singleBtnClickListener != null) {
                    singleBtnClickListener.clickBtn(type);
                }
            }
        });
        dialog.setContentView(contentView);
        dialog.show();
    }

    /**
     * @param title        对话框标题
     * @param content      内容
     * @param btnLeftText  按钮文字 为空时传null 或"" 默认为取消
     * @param btnRightText 按钮文字 为空时传null 或"" 默认为确定
     * @return void 返回类型
     * @Title: showDialogSingleBtn
     * @Description: 显示提示对话框，带一个确认按钮
     * @author yanzi
     */
    public void showDialogDoubleBtn(String title, String content, String btnLeftText, String btnRightText, final int type) {
        View contentView = initBaseDialog(title, content);
        // 设置按钮
        TextView tv_dg_single = (TextView) contentView
                .findViewById(R.id.tv_dg_single);
        TextView tv_dg_double_left = (TextView) contentView
                .findViewById(R.id.tv_dg_double_left);
        TextView tv_dg_double_right = (TextView) contentView
                .findViewById(R.id.tv_dg_double_right);

        tv_dg_single.setVisibility(View.GONE);

        if (!Tools.isNull(btnLeftText)) {
            tv_dg_double_left.setText(btnLeftText);
        }
        if (!Tools.isNull(btnRightText)) {
            tv_dg_double_right.setText(btnRightText);
        }


        tv_dg_double_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (dialogDoubleBtnClickListener != null) {
                    dialogDoubleBtnClickListener.clickLeftBtn(type);
                }
            }
        });

        tv_dg_double_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (dialogDoubleBtnClickListener != null) {
                    dialogDoubleBtnClickListener.clickRightBtn(type);
                }
            }
        });
        dialog.setContentView(contentView);
        dialog.show();
    }

    private View initBaseDialog(String title, String content) {
        if (mBaseAty.isFinishing()) {
            return null;
        }
        dialog = new Dialog(mBaseAty, R.style.base_aty_dialog);
        View contentView = mLayoutInflater.inflate(R.layout.dg_base_manager, null);
        dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialog.setCancelable(false);

        // 初始化控件
        TextView tv_dg_title = (TextView) contentView
                .findViewById(R.id.tv_dg_title);
        TextView tv_dg_content = (TextView) contentView
                .findViewById(R.id.tv_dg_content);


        View v_dg_divider_10 = contentView
                .findViewById(R.id.v_dg_divider_10);


        // 标题不为空 内容不为空
        if (!Tools.isNull(title) && !Tools.isNull(content)) {
            tv_dg_title.setText(title);
            tv_dg_content.setText(content);
        } else {
            if (!Tools.isNull(title) && Tools.isNull(content)) {
                tv_dg_title.setText(title);
                v_dg_divider_10.setVisibility(View.GONE);
                tv_dg_content.setVisibility(View.GONE);
            } else {
                tv_dg_title.setVisibility(View.GONE);
                tv_dg_content.setText(content);
            }
        }
        return contentView;
    }


}
