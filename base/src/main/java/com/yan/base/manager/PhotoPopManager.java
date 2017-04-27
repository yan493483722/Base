package com.yan.base.manager;


import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yan.base.BaseAty;
import com.yan.base.R;


/**
 * Created by YanZi on 2016/12/06.
 * describe：弹出选择相册图片和从拍照对话框
 * modify:
 * modify date:
 */
public class PhotoPopManager {
    /**
     * 选择 相机 相册 的PopupWindow
     * <p/>
     * 初始化 manager 后可以方便调用
     */
    private PopupWindow pop;
    /**
     * Pop 的布局
     */
    private LinearLayout ll_popup;

    private Activity mAty;

    private BottomPopClickListener bottomPopClickListener;

    private LayoutInflater mLayoutInflater;

    private String arg[];
    /**
     * 图片上传 构造函数
     *
     * @param mAty intent 相机相册页面打开的intent
     */
    public PhotoPopManager(BaseAty mAty, LayoutInflater mLayoutInflater, BottomPopClickListener bottomPopClickListener) {
        this.mAty = mAty;
        this.bottomPopClickListener = bottomPopClickListener;
        this.mLayoutInflater = mLayoutInflater;
        // 初始化 Pop

    }



    /**
     * 初始化 Pop
     */
    private void initPop() {
        pop = new PopupWindow(mAty);

        View view = mLayoutInflater.inflate(R.layout.pop_photo_select, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        pop.setBackgroundDrawable(dw);
        backgroundAlpha(mAty, 0.5f);//0.0-1.0
        pop.setFocusable(true);
        pop.setOutsideTouchable(false);

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(mAty, 1);//0.0-1.0
            }
        });
        pop.setContentView(view);

        TextView tv_photo_top = (TextView) view
                .findViewById(R.id.tv_photo_top);
        TextView tv_photo_middle = (TextView) view
                .findViewById(R.id.tv_photo_middle);

        TextView tv_photo_bottom = (TextView) view
                .findViewById(R.id.tv_photo_bottom);


        tv_photo_top.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bottomPopClickListener != null) {
                    bottomPopClickListener.clickTop();
                }

                pop.dismiss();
                ll_popup.clearAnimation();

            }
        });

        tv_photo_middle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bottomPopClickListener != null) {
                    bottomPopClickListener.clickMiddle();
                }
                pop.dismiss();
                ll_popup.clearAnimation();

            }
        });

        tv_photo_bottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bottomPopClickListener != null) {
                    bottomPopClickListener.clickBottom();
                }
                pop.dismiss();
                ll_popup.clearAnimation();

            }
        });
    }

    /**
     * 显示 Pop
     */
    public void showPop() {
        initPop();
        showPop(mAty.getWindow().getDecorView(), Gravity.BOTTOM);
    }

    /**
     * 显示 Pop
     *
     * @param parents 显示pop的Parents View
     * @param gravity 显示pop的位置
     */
    public void showPop(View parents, int gravity) {
        initPop();
        ll_popup.startAnimation(AnimationUtils.loadAnimation(mAty, R.anim.slide_in_from_bottom));
        pop.showAtLocation(parents, gravity, 0, 0);
        backgroundAlpha(mAty, 0.5f);//0.0-1.0
    }


    public interface BottomPopClickListener {

        void clickTop();

        void clickMiddle();

        void clickBottom();
    }

    //=============================================================================================

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        activity.getWindow().setAttributes(lp);
    }


    RecyclerView recyclerView;


}
