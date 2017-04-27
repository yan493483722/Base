package com.yan.basedemo.aty;

import android.view.View;
import android.widget.Button;

import com.yan.base.BaseAty;
import com.yan.base.dialog.BaseDoubleBtnDialog;
import com.yan.base.dialog.BaseInputDoubleBtnDialog;
import com.yan.base.dialog.BaseSingleBtnDialog;
import com.yan.base.dialog.BaseSingleInputDoubleBtnDialog;
import com.yan.base.listener.BaseDialogDoubleBtnClickListener;
import com.yan.base.listener.BaseDialogInputDoubleBtnClickListener;
import com.yan.base.listener.BaseDialogSingleBtnClickListener;
import com.yan.base.manager.PhotoPopManager;
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
    private static final int DIALOG_TYPE_TWO = 2;
    private static final int DIALOG_TYPE_THREE = 3;
    private static final int DIALOG_TYPE_FOUR = 4;
    private static final int DIALOG_TYPE_FIVE = 5;

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
    @BindView(R.id.btn_dialog_photo)
    Button btnDialogPhoto;

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

    }

    @OnClick({R.id.btn_dialog_example_single, R.id.btn_dialog_example_double
            , R.id.btn_dialog_example_input_one, R.id.btn_dialog_example_input
            , R.id.btn_dialog_example_input_password,R.id.btn_dialog_photo})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_example_single:
//                mDialogManager.showDialogSingleBtn("title", "content", DIALOG_TYPE_ONE);

                new BaseSingleBtnDialog.Builder(mAty, mLayoutInflater).
                        setBaseDialogSingleBtnClickListener(mBaseDialogSingleBtnClickListener).
                        setType(DIALOG_TYPE_ONE).setContent("见到你很高兴，你好你好")
                        .setTitle("你好").create().show();
                new BaseSingleBtnDialog.Builder(mAty, mLayoutInflater)
                        .setBaseDialogSingleBtnClickListener(mBaseDialogSingleBtnClickListener)
                        .setType(DIALOG_TYPE_ONE).setContent("见到你很高兴，你好你好")
                        .create().show();
                new BaseSingleBtnDialog.Builder(mAty, mLayoutInflater)
                        .setBaseDialogSingleBtnClickListener(mBaseDialogSingleBtnClickListener)
                        .setType(DIALOG_TYPE_ONE).setTitle("见到你很高兴，你好你好")
                        .create().show();
                break;
            case R.id.btn_dialog_example_double:
                new BaseDoubleBtnDialog.Builder(mAty, mLayoutInflater).
                        setBaseDialogDoubleBtnClickListener(mBaseDialogDoubleBtnClickListener)
                        .setType(DIALOG_TYPE_TWO).setContent("见到你很高兴，你好你好")
                        .setTitle("你好")
                        .create().show();
                new BaseDoubleBtnDialog.Builder(mAty, mLayoutInflater)
                        .setBaseDialogDoubleBtnClickListener(mBaseDialogDoubleBtnClickListener)
                        .setType(DIALOG_TYPE_TWO).setContent("见到你很高兴，你好你好")
                        .create().show();
                new BaseDoubleBtnDialog.Builder(mAty, mLayoutInflater)
                        .setBaseDialogDoubleBtnClickListener(mBaseDialogDoubleBtnClickListener)
                        .setType(DIALOG_TYPE_TWO).setTitle("见到你很高兴，你好你好")
                        .create().show();
                break;
            case R.id.btn_dialog_example_input_one:
                new BaseSingleInputDoubleBtnDialog.Builder(mAty, mLayoutInflater)
                        .setBaseDialogInputDoubleBtnClickListener(mBaseDialogInputDoubleBtnClickListener)
                        .setType(DIALOG_TYPE_THREE).setContent("张三丰")
                        .setTitle("张三丰").create().show();

                break;
            case R.id.btn_dialog_example_input:
                new BaseInputDoubleBtnDialog.Builder(mAty, mLayoutInflater)
                        .setTextHint("请输入密码")
                        .setBaseDialogInputDoubleBtnClickListener(mBaseDialogInputDoubleBtnClickListener)
                        .setType(DIALOG_TYPE_FOUR).setTitle("你好").create().show();

                break;
            case R.id.btn_dialog_photo:
                PhotoPopManager photoPopManager=new PhotoPopManager(mAty, mLayoutInflater, new PhotoPopManager.BottomPopClickListener() {
                    @Override
                    public void clickTop() {
                        mSnackBarAndToastManager.showSnackBar("clickTop");
                    }

                    @Override
                    public void clickMiddle() {
                        mSnackBarAndToastManager.showSnackBar("clickMiddle");
                    }

                    @Override
                    public void clickBottom() {
                        mSnackBarAndToastManager.showSnackBar("clickBottom");
                    }
                });
                photoPopManager.showPop();
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

    private BaseDialogInputDoubleBtnClickListener mBaseDialogInputDoubleBtnClickListener = new BaseDialogInputDoubleBtnClickListener() {
        @Override
        public void clickLeftBtn(int type, String text) {
            switch (type) {
                case DIALOG_TYPE_THREE:
                    mSnackBarAndToastManager.showSnackBar("this is type double left \n input :" + text);
                    break;
                case DIALOG_TYPE_FOUR:
                    mSnackBarAndToastManager.showSnackBar("this is type double left \n input :" + text);
                    break;
            }
        }

        @Override
        public void clickRightBtn(int type, String text) {
            switch (type) {
                case DIALOG_TYPE_THREE:
                    mSnackBarAndToastManager.showSnackBar("this is type double right \n input :" + text);
                    break;
                case DIALOG_TYPE_FOUR:
                    mSnackBarAndToastManager.showSnackBar("this is type double right \n input :" + text);
                    break;
            }
        }
    };

}
