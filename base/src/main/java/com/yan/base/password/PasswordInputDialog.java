package com.yan.base.password;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yan.base.BaseAty;
import com.yan.base.R;
import com.yan.base.loading.PassWordProgressbar;

/**
 * Created by YanZi on 2017/5/8.
 * describe：Dialog fragment 的好处，1.跟生命周期绑定 2.在平板上面会自动变大
 * 注意：dismissed 的时候会自动从fragmentManager 中移除
 * modify:
 * modify date:
 */
public class PasswordInputDialog extends DialogFragment implements View.OnClickListener {

    public String ACTIVITY_TAG = "BaseAty";


    private TextView tv_dg_password_cancel, tv_dg_password_forget;

    private PasswordInputView piv_dg_password;

    private PasswordKeyboard pk_dg_password;

    private LinearLayout ll_dg_password;

    private PassWordProgressbar pwp_dg_password;

    private BaseAty mAty;

    PasswordInputDialogListener passwordInputDialogListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dg_password_input, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //去除弹窗的样式
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0xffffffff));
        window.setLayout(displayMetrics.widthPixels, window.getAttributes().height);
        window.setWindowAnimations(R.style.slid_bottom_in_out);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAty = (BaseAty) getActivity();
        tv_dg_password_cancel = (TextView) view.findViewById(R.id.tv_dg_password_cancel);
        tv_dg_password_forget = (TextView) view.findViewById(R.id.tv_dg_password_forget);
        piv_dg_password = (PasswordInputView) view.findViewById(R.id.piv_dg_password);
        pk_dg_password = (PasswordKeyboard) view.findViewById(R.id.pk_dg_password);
        ll_dg_password = (LinearLayout) view.findViewById(R.id.ll_dg_password);
        pwp_dg_password = (PassWordProgressbar) view.findViewById(R.id.pwp_dg_password);

        tv_dg_password_cancel.setOnClickListener(this);
        tv_dg_password_forget.setOnClickListener(this);
        pk_dg_password.setClickKeyListener(clickKeyListener);
        piv_dg_password.setPasswordInputListener(passwordInputDialogListener);
        pwp_dg_password.setAnimationEndListener(passwordInputDialogListener);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        // 因为id的编译  lib里面不能使用switch
        if (i == R.id.tv_dg_password_cancel) {
            if (passwordInputDialogListener != null) {
                passwordInputDialogListener.cancel();
            }
        } else if (i == R.id.tv_dg_password_forget) {
            if (passwordInputDialogListener != null) {
                passwordInputDialogListener.forgetPassword();
            }
        } else {
        }
    }

    public void reset() {
        piv_dg_password.clearPassword();
        ll_dg_password.setVisibility(View.VISIBLE);
        pwp_dg_password.setVisibility(View.GONE);
    }

    public void loadSuccess(CharSequence successText) {
        pwp_dg_password.setVisibility(View.VISIBLE);
        ll_dg_password.setVisibility(View.INVISIBLE);
        pwp_dg_password.loadSuccess(successText);
    }

    public void loadFail(CharSequence failText) {
        pwp_dg_password.setVisibility(View.VISIBLE);
        ll_dg_password.setVisibility(View.INVISIBLE);
        pwp_dg_password.loadFail(failText);
    }

    /**
     * 开始加载
     */
    public void loadArc(CharSequence loadText) {
        pwp_dg_password.setVisibility(View.VISIBLE);
        ll_dg_password.setVisibility(View.INVISIBLE);
        pwp_dg_password.loadArc(loadText);
    }

    /**
     * 如果在加载loading 就取消loading 如果再加载success 就取消success 如果在加载 fail 就取消fail
     */
    public void cancelAllLoading() {
        pwp_dg_password.cancelAllLoading();
    }

    private PasswordKeyboard.ClickKeyListener clickKeyListener = new PasswordKeyboard.ClickKeyListener() {
        @Override
        public void clickDelete() {
            piv_dg_password.deletePassword();
        }

        @Override
        public void clickClear() {
            piv_dg_password.clearPassword();
        }

        @Override
        public void clickNum(int num) {
            piv_dg_password.setPassword(String.valueOf(num));
        }
    };


    public interface PasswordInputDialogListener extends PassWordProgressbar.AnimationEndListener, PasswordInputView.PasswordInputListener {

        void cancel();

        void forgetPassword();
    }

    public void setPasswordInputDialogListener(PasswordInputDialogListener passwordInputDialogListener) {
        this.passwordInputDialogListener = passwordInputDialogListener;
    }
}
