package com.yan.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yan.base.R;
import com.yan.base.listener.BaseDialogSingleBtnClickListener;

import java.util.ArrayList;

/**
 * Created by YanZi on 2017/8/17.
 * describe：
 * modify:
 * modify date:
 */
public class BaseThreeBtnDialog extends BaseDialog {

    public Context mContext;

    public BaseThreeBtnDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseThreeBtnDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;


    }

    protected BaseThreeBtnDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public static class Builder extends BaseDialog.Builder {

        protected ArrayList<String> btnText;


        protected BaseDialogSingleBtnClickListener baseDialogSingleBtnClickListener;

        public BaseThreeBtnDialog.Builder setBaseThreeBtnDialog(BaseDialogSingleBtnClickListener baseDialogSingleBtnClickListener) {
            this.baseDialogSingleBtnClickListener = baseDialogSingleBtnClickListener;
            return this;
        }

        public void setBtnText(ArrayList<String> btnText) {
            this.btnText = btnText;
        }


        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
        }

        @Override
        void initBtn(FrameLayout fl_dg_bottom, final Dialog dialog) {
            final View view = LayoutInflater.from(context).inflate(R.layout.dg_base_bottom_btn_more, fl_dg_bottom, false);
            fl_dg_bottom.removeAllViews();
            fl_dg_bottom.addView(view);

            // 设置按钮
            RecyclerView tv_dg_single = (RecyclerView) view
                    .findViewById(R.id.rv_tv_dg_single);

            ThreeMoreBtnAdapter messageDetailAdapter = new ThreeMoreBtnAdapter(context, btnText);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            tv_dg_single.setLayoutManager(linearLayoutManager);
            tv_dg_single.setAdapter(messageDetailAdapter);


        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_content;
        }

        class ThreeMoreBtnAdapter extends RecyclerView.Adapter<ThreeMoreBtnAdapter.ViewHolder> {

            private ArrayList<String> btnTextList;

            private Context mContext;

            public ThreeMoreBtnAdapter(Context mContext, ArrayList<String> btnTextList) {
                this.mContext = mContext;
                this.btnTextList = btnTextList;
            }

            @Override
            public ThreeMoreBtnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.dg_base_bottom_btn_single, parent, false);
                return new ThreeMoreBtnAdapter.ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ThreeMoreBtnAdapter.ViewHolder holder, int position) {
                if (position < btnTextList.size() - 1) {
                    holder.tv_dg_single.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_rect_white_gray));
//               holder.tv_dg_single.setBackground(mContext.getResources().getDrawable(R.drawable.selector_rect_white_gray));
                    holder.tv_dg_single.setText(btnTextList.get(position));
                } else {
                    holder.tv_dg_single.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_dg_single));
                }
                holder.tv_dg_single.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @Override
            public int getItemCount() {
                return btnTextList == null ? 0 : btnTextList.size();
            }


            class ViewHolder extends RecyclerView.ViewHolder {

                TextView tv_dg_single;//消息推送时间

                public ViewHolder(View itemView) {
                    super(itemView);
                    tv_dg_single = (TextView) itemView.findViewById(R.id.tv_dg_single);
                }
            }
        }
    }


}
