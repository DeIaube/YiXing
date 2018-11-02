package arouter.dawn.zju.edu.module_mine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_mine.ui.setting.SettingActivity;
import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_MINE)
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
        view.findViewById(R.id.feedback).setOnClickListener(this);
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
            startActivity(new Intent(getContext(), SettingActivity.class));
        } else if (id == R.id.account_detail) {
            // 跳转个人详情页
        } else if (id == R.id.wallet_layout) {
            // 点击钱包
        }  else if (id == R.id.cash_coupon_layout) {
            // 点击代金券
        } else if (id == R.id.integral_layout) {
            // 点击积分
            showMessage(getString(R.string.bug_bug_bug_can_rise_integral));
        } else if (id == R.id.collection_layout) {
            // 点击我的收藏
            ARouter.getInstance().build(Constants.AROUTER_SETTING_COLLECTION).navigation();
        } else if (id == R.id.integral_mall_layout) {
            // 点击积分商城
        } else if (id == R.id.sign_in_obtain_cash_coupon_layout) {
            // 点击签到领券
        } else if (id == R.id.feedback) {
            // 点击问题反馈
            ARouter.getInstance().build(Constants.AROUTER_SETTING_FEEDBACK).navigation();
        } else if (id == R.id.share_layout) {
            // 点击分享好友
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, R.string.share_content);
            shareIntent.setType("text/plain");
            startActivity(shareIntent.createChooser(shareIntent, getString(R.string.share_to)));
        } else if (id == R.id.cooperate_layout) {
            // 点击与我合作
            new AlertDialog.Builder(getContext())
                    .setTitle(getString(R.string.tips))
                    .setMessage(getString(R.string.github_mine))
                    .setPositiveButton(R.string.confirm, null)
                    .show();
        } else if (id == R.id.about_layout) {
            // 点击关于项目
        } else if (id == R.id.github_layout) {
            // 点击项目源码
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(getString(R.string.github_mine));//此处填链接
            intent.setData(content_url);
            startActivity(intent);
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
