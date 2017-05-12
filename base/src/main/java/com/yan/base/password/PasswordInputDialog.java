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
import android.widget.TextView;

import com.yan.base.R;

/**
 * Created by YanZi on 2017/5/8.
 * describe：Dialog fragment 的好处，1.跟生命周期绑定 2.在平板上面会自动变大
 * 注意：dismissed 的时候会自动从fragmentManager 中移除
 * modify:
 * modify date:
 */
public class PasswordInputDialog extends DialogFragment implements View.OnClickListener {

    private TextView tv_dg_password_cancel, tv_dg_password_forget;

    private PasswordInputView piv_dg_password;

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
        tv_dg_password_cancel = (TextView) view.findViewById(R.id.tv_dg_password_cancel);
        tv_dg_password_forget = (TextView) view.findViewById(R.id.tv_dg_password_forget);
        piv_dg_password = (PasswordInputView) view.findViewById(R.id.piv_dg_password);
        tv_dg_password_cancel.setOnClickListener(this);
        tv_dg_password_forget.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        // 因为id的编译  lib里面不能使用switch
        if (i == R.id.tv_dg_password_cancel) {
            piv_dg_password.deletePassword();
//            dismiss();
        } else if (i == R.id.tv_dg_password_forget) {
//            dismiss();
            piv_dg_password.setPassword("a");
        } else {
        }
    }
}
