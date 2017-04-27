package com.yan.basedemo.aty;

import android.view.View;
import android.widget.Button;

import com.yan.base.BaseAty;
import com.yan.base.dialog.BaseDoubleBtnDialog;
import com.yan.base.dialog.BaseSingleBtnDialog;
import com.yan.base.listener.BaseDialogDoubleBtnClickListener;
import com.yan.base.listener.BaseDialogSingleBtnClickListener;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YanZi on 2017/4/14.
 * describe：
 * modify:
 * modify date:
 */
public class DialogExampleAty extends BaseAty {

    //类型
    private static final int DIALOG_TYPE_ONE = 1;
    private static final int DIALOG_TYPE_TWO = 1;
    private static final int DIALOG_TYPE_THREE = 1;
    private static final int DIALOG_TYPE_FOUR = 1;
    private static final int DIALOG_TYPE_FIVE = 1;

    @BindView(R.id.btn_dialog_example_single)
    Button btnDialogExampleSingle;
    @BindView(R.id.btn_dialog_example_double)
    Button btnDialogExampleDouble;
    @BindView(R.id.btn_dialog_example_input_one)
    Button btnDialogExampleInputOne;
    @BindView(R.id.btn_dialog_example_input)
    Button btnDialogExampleInput;
    @BindView(R.id.btn_dialog_example_input_password)
    Button btnDialogExampleInputPassword;

    @Override
    protected void initContentView() {
        setContentView(R.layout.aty_dialog_example);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        mDialogManager.setBaseDialogSingleBtnClickListener(mBaseDialogSingleBtnClickListener);
        mDialogManager.setBaseDialogDoubleBtnClickListener(mBaseDialogDoubleBtnClickListener);
    }

    @OnClick({R.id.btn_dialog_example_single, R.id.btn_dialog_example_double
            , R.id.btn_dialog_example_input_one, R.id.btn_dialog_example_input
            , R.id.btn_dialog_example_input_password})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_example_single:
//                mDialogManager.showDialogSingleBtn("title", "content", DIALOG_TYPE_ONE);

                new BaseSingleBtnDialog.Builder(mAty,mLayoutInflater).setContent("丢你螺母").setTitle("粤语").create().show();
                new BaseSingleBtnDialog.Builder(mAty,mLayoutInflater).setContent("你妈了个比的傻逼东西，你妈了个比的").create().show();
                new BaseSingleBtnDialog.Builder(mAty,mLayoutInflater).setTitle("顶你个肺啊傻逼东西").create().show();
                break;
            case R.id.btn_dialog_example_double:
                new BaseDoubleBtnDialog.Builder(mAty,mLayoutInflater).setContent("丢你螺母").setTitle("粤语").create().show();
                new BaseDoubleBtnDialog.Builder(mAty,mLayoutInflater).setContent("你妈了个比的傻逼东西，你妈了个比的").create().show();
                new BaseDoubleBtnDialog.Builder(mAty,mLayoutInflater).setTitle("顶你个肺啊傻逼东西").create().show();
                break;
            case R.id.btn_dialog_example_input_one:
                break;
            case R.id.btn_dialog_example_input:
                break;
            case R.id.btn_dialog_example_input_password:
                break;
        }
    }


    private BaseDialogSingleBtnClickListener mBaseDialogSingleBtnClickListener = new BaseDialogSingleBtnClickListener() {

        @Override
        public void clickBtn(int type) {
            switch (type) {
                case DIALOG_TYPE_ONE:
                    mSnackBarAndToastManager.showSnackBar("this is type single");
                    break;
            }
        }
    };


    private BaseDialogDoubleBtnClickListener mBaseDialogDoubleBtnClickListener = new BaseDialogDoubleBtnClickListener() {

        @Override
        public void clickLeftBtn(int type) {
            switch (type) {
                case DIALOG_TYPE_TWO:
                    mSnackBarAndToastManager.showSnackBar("this is type double left");
                    break;
            }
        }

        @Override
        public void clickRightBtn(int type) {
            switch (type) {
                case DIALOG_TYPE_TWO:
                    mSnackBarAndToastManager.showSnackBar("this is type double right");
                    break;
            }
        }
    };

}
