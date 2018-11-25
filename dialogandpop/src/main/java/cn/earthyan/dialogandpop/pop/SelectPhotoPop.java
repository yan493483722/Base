package cn.earthyan.dialogandpop.pop;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.earthyan.dialogandpop.R;


/**
 * Created by YanZi on 2016/12/06.
 * describe：弹出选择相册图片和从拍照对话框,
 * 自定义属性暂未抽取，pop很少写在布局文件中
 * modify:
 * modify date:
 */
public class SelectPhotoPop extends PopupWindow {

    /**
     * isEndAnimationOver
     */
    private boolean isEnd;

    /**
     * Pop 的布局
     */
    private LinearLayout ll_popup;

    private Activity mAty;

    private BottomPopClickListener bottomPopClickListener;

    private LayoutInflater mLayoutInflater;
    //text outside change;
    private String arg[];

    public SelectPhotoPop(Context context) {
        super(context, null);
    }

    /**
     * 图片上传 构造函数
     *
     * @param mAty intent 相机相册页面打开的intent
     */
    public SelectPhotoPop(Activity mAty, LayoutInflater mLayoutInflater, BottomPopClickListener bottomPopClickListener) {
        this(mAty);
        this.mAty = mAty;
        this.bottomPopClickListener = bottomPopClickListener;
        this.mLayoutInflater = mLayoutInflater;
    }


    /**
     * 初始化 Pop
     */
    private void initPop() {
        View view = mLayoutInflater.inflate(R.layout.pop_photo_select, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        setBackgroundDrawable(dw);
        backgroundAlpha(mAty, 0.5f);//0.0-1.0
        setFocusable(true);
        setOutsideTouchable(false);
        setContentView(view);
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
                dismiss();

            }
        });

        tv_photo_middle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bottomPopClickListener != null) {
                    bottomPopClickListener.clickMiddle();
                }
                dismiss();

            }
        });

        tv_photo_bottom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bottomPopClickListener != null) {
                    bottomPopClickListener.clickBottom();
                }
                dismiss();

            }
        });
    }

    /**
     * 显示 Pop
     */
    public void showPop() {
        initPop();
        isEnd = false;
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
//        pop.setAnimationStyle(R.style.slid_bottom_in_out);
        showAtLocation(parents, gravity, 0, 0);
        backgroundAlpha(mAty, 0.5f);//0.0-1.0
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity activity, float bgAlpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha;
//      activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//      activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(lp);
    }

    public void dismiss() {
        if (isEnd) {
            backgroundAlpha(mAty, 1);
            super.dismiss();
            return;
        }
        //动画  上升-->下降
        Animation animationEnd = AnimationUtils.loadAnimation(mAty, R.anim.slide_out_from_bottom);
        animationEnd.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isEnd = true;
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ll_popup.startAnimation(animationEnd);
    }

    //===============================the interface============================================
    public interface BottomPopClickListener {

        void clickTop();

        void clickMiddle();

        void clickBottom();
    }
}
