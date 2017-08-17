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
import com.yan.base.listener.BaseDialogThreeMoreBtnClickListener;
import com.yan.base.uitls.RecyclerListDiv;

import java.util.ArrayList;

/**
 * Created by YanZi on 2017/8/17.
 * describe：
 * modify:
 * modify date:
 */
public class BaseThreeMoreBtnDialog extends BaseDialog {

    public Context mContext;

    public BaseThreeMoreBtnDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseThreeMoreBtnDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;


    }

    protected BaseThreeMoreBtnDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public static class Builder extends BaseDialog.Builder {

        protected ArrayList<String> btnText;


        protected BaseDialogThreeMoreBtnClickListener baseDialogThreeMoreBtnClickListener;

        public BaseThreeMoreBtnDialog.Builder setBaseDialogThreeMoreBtnClickListener(BaseDialogThreeMoreBtnClickListener baseDialogThreeMoreBtnClickListener) {
            this.baseDialogThreeMoreBtnClickListener = baseDialogThreeMoreBtnClickListener;
            return this;
        }

        public Builder setBtnText(ArrayList<String> btnText) {
            this.btnText = btnText;
            return this;
        }


        public Builder(Activity context, LayoutInflater mLayoutInflater) {
            super(context, mLayoutInflater);
        }

        @Override
        void initBtn(FrameLayout fl_dg_bottom, final Dialog dialog) {
            final View view = LayoutInflater.from(context).inflate(R.layout.dg_base_bottom_btn_three_more, fl_dg_bottom, false);
            fl_dg_bottom.removeAllViews();
            fl_dg_bottom.addView(view);

            // 设置按钮
            RecyclerView rv_dg_base_bottom = (RecyclerView) view
                    .findViewById(R.id.rv_dg_base_bottom);

            ThreeMoreBtnAdapter messageDetailAdapter = new ThreeMoreBtnAdapter(context, btnText,dialog);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

            rv_dg_base_bottom.addItemDecoration(new RecyclerListDiv(RecyclerListDiv.HORIZONTAL, 2, context.getResources().getColor(R.color.divider_line_color)));

            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            rv_dg_base_bottom.setLayoutManager(linearLayoutManager);
            rv_dg_base_bottom.setAdapter(messageDetailAdapter);


        }

        @Override
        int setContentLayout() {
            return R.layout.dg_base_content;
        }

        class ThreeMoreBtnAdapter extends RecyclerView.Adapter<ThreeMoreBtnAdapter.ViewHolder> {

            private ArrayList<String> btnTextList;

            private Context mContext;

            private Dialog dialog;

            public ThreeMoreBtnAdapter(Context mContext, ArrayList<String> btnTextList, Dialog dialog) {
                this.mContext = mContext;
                this.btnTextList = btnTextList;
                this.dialog = dialog;
            }

            @Override
            public ThreeMoreBtnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.dg_base_bottom_btn_single, parent, false);
                return new ThreeMoreBtnAdapter.ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ThreeMoreBtnAdapter.ViewHolder holder, final int position) {
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
                        if (baseDialogThreeMoreBtnClickListener != null) {
                            baseDialogThreeMoreBtnClickListener.clickBtn(position, type);
                        }
                        dialog.dismiss();
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
