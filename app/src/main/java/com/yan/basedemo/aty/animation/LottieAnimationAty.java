package com.yan.basedemo.aty.animation;

import android.animation.Animator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YanZi on 2019/6/29 .
 * Description（描述）：
 * Modify(修改) :
 * Modify Description (修改描述):
 */
public class LottieAnimationAty extends BaseAty {

    @BindView(R.id.lottie_animation_view)
    LottieAnimationView lottieAnimationView;
    @BindView(R.id.lottie_animation_recycle)
    LottieAnimationView lottieAnimationRecycle;
    @BindView(R.id.lottie_animation_pog_champ)
    LottieAnimationView lottieAnimationPogChamp;

    @Override
    protected int setContentLayout() {
        return R.layout.aty_lottie_animation;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {
        lottieAnimationView.setSpeed(1.5f);
        lottieAnimationRecycle.setSpeed(3.5f);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
