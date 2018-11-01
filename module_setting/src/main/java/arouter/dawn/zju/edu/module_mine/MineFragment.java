package arouter.dawn.zju.edu.module_mine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.TextView;

import baselib.base.BaseFragment;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    // 钱包余额 0.00元
    TextView walletBalanceTv;
    // 代金券 6个
    TextView cashCouponBalanceTv;
    // 积分 6个
    TextView integralBalanceTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {
        walletBalanceTv = view.findViewById(R.id.wallet_balance);
        cashCouponBalanceTv = view.findViewById(R.id.cash_coupon_balance);
        integralBalanceTv = view.findViewById(R.id.integral_balance);

        view.findViewById(R.id.notice).setOnClickListener(this);
        view.findViewById(R.id.setting).setOnClickListener(this);
        view.findViewById(R.id.account_detail).setOnClickListener(this);
        view.findViewById(R.id.wallet_layout).setOnClickListener(this);
        view.findViewById(R.id.cash_coupon_layout).setOnClickListener(this);
        view.findViewById(R.id.integral_layout).setOnClickListener(this);

        view.findViewById(R.id.collection_layout).setOnClickListener(this);
        view.findViewById(R.id.integral_mall_layout).setOnClickListener(this);
        view.findViewById(R.id.sign_in_obtain_cash_coupon_layout).setOnClickListener(this);
        view.findViewById(R.id.customer_service_layout).setOnClickListener(this);
        view.findViewById(R.id.share_layout).setOnClickListener(this);
        view.findViewById(R.id.cooperate_layout).setOnClickListener(this);
        view.findViewById(R.id.about_layout).setOnClickListener(this);
        view.findViewById(R.id.github_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        int id = v.getId();
        if (id == R.id.notice) {
            // 跳转到当前通知
        } else if (id == R.id.setting) {
            // 跳转到设置页面
        } else if (id == R.id.account_detail) {
            // 跳转个人详情页
        } else if (id == R.id.wallet_layout) {
            // 点击钱包
        }  else if (id == R.id.cash_coupon_layout) {
            // 点击代金券
        } else if (id == R.id.integral_layout) {
            // 点击积分
        } else if (id == R.id.collection_layout) {
            // 点击我的收藏
        } else if (id == R.id.integral_mall_layout) {
            // 点击积分商城
        } else if (id == R.id.sign_in_obtain_cash_coupon_layout) {
            // 点击签到领券
        } else if (id == R.id.customer_service_layout) {
            // 点击客服中心
        } else if (id == R.id.share_layout) {
            // 点击分享好友
        } else if (id == R.id.cooperate_layout) {
            // 点击与我合作
        } else if (id == R.id.about_layout) {
            // 点击关于项目
        } else if (id == R.id.github_layout) {
            // 点击项目源码
        }
        showBtnShadow(v);
    }

    private void showBtnShadow(final View v) {
        v.animate()
                .translationZ(15f).setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        v.animate()
                                .translationZ(1.0f).setDuration(500);
                    }
                }).start();
    }
}
