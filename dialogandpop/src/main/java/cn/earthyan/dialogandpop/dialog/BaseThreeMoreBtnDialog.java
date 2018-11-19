package cn.earthyan.dialogandpop.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.earthyan.dialogandpop.R;
import cn.earthyan.dialogandpop.listener.BaseDialogThreeMoreBtnClickListener;
import cn.earthyan.dialogandpop.util.RecyclerListDiv;

/**
 * Created by YanZi on 2017/8/17.
 * describe：
 * modify:
 * modify date:
 */
public class BaseThreeMoreBtnDialog extends BaseDialog {

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

            ThreeMoreBtnAdapter messageDetailAdapter = new ThreeMoreBtnAdapter(context, btnText, dialog);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

            rv_dg_base_bottom.addItemDecoration(new RecyclerListDiv(RecyclerListDiv.HORIZONTAL, 1, context.getResources().getColor(R.color.divider_line_color)));

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
                if (null != btnTextList.get(position)) {
                    holder.tv_dg_single.setText(btnTextList.get(position));
                }
                if (position < btnTextList.size() - 1) {
                    holder.tv_dg_single.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_rect_white_gray));
//               holder.tv_dg_single.setBackground(mContext.getResources().getDrawable(R.drawable.selector_rect_white_gray));
                } else {
                    holder.tv_dg_single.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_dg_single));
                }
                holder.tv_dg_single.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (baseDialogThreeMoreBtnClickListener != null) {
                            baseDialogThreeMoreBtnClickListener.clickBtn(position, tag);
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


        @Override
        public Builder setTitle(String title) {
            super.setTitle(title);
            return this;
        }

        @Override
        public Builder setContent(String content) {
            super.setContent(content);
            return this;
        }

        @Override
        public Builder setTag(int tag) {
            super.setTag(tag);
            return this;
        }

        @Override
        public Builder setCloseVisible(int visibility) {
            super.setCloseVisible(visibility);
            return this;
        }


        @Override
        public Builder setContentTextGravity(int textContentGravity) {
            super.setContentTextGravity(textContentGravity);
            return this;
        }

        @Override
        public Builder setTitleTextSize(int titleTextSize) {
            super.setTitleTextSize(titleTextSize);
            return this;
        }

        @Override
        public Builder setTitleTextColorInt(int titleTextColor) {
            super.setTitleTextColorInt(titleTextColor);
            return this;
        }

        @Override
        public Builder setTitleTextColorRes(int titleTextColor) {
            super.setTitleTextColorRes(titleTextColor);
            return this;
        }

        @Override
        public Builder setContentTextSize(int contentTextSize) {
            super.setContentTextSize(contentTextSize);
            return this;
        }

        @Override
        public Builder setContentTextColorInt(int contentTextColor) {
            super.setContentTextColorInt(contentTextColor);
            return this;
        }

        @Override
        public Builder setContentTextColorRes(int contentTextColor) {
            super.setContentTextColorRes(contentTextColor);
            return this;
        }

        @Override
        public Builder setTitleTextBold(boolean titleTextBold) {
            super.setTitleTextBold(titleTextBold);
            return this;
        }

        @Override
        public Builder setBtnTextSize(int btnTextSize) {
            super.setBtnTextSize(btnTextSize);
            return this;
        }

        @Override
        public Builder setBtnColorStateList(int btnColorStateList) {
            super.setBtnColorStateList(btnColorStateList);
            return this;
        }
    }

}
