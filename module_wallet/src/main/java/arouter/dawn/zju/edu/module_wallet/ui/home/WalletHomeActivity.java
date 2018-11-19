package arouter.dawn.zju.edu.module_wallet.ui.home;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_WALLET_HOME)
public class WalletHomeActivity extends BaseActivity<WalletHomeContract.Presenter> implements
        WalletHomeContract.View, View.OnClickListener {

    TextView homeBalanceTv;

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        homeBalanceTv = findViewById(R.id.wallet_home_balance);
        findViewById(R.id.wallet_home_forward).setOnClickListener(this);
        findViewById(R.id.wallet_home_recharge).setOnClickListener(this);
        findViewById(R.id.wallet_home_bill).setOnClickListener(this);
        findViewById(R.id.wallet_home_question).setOnClickListener(this);
        findViewById(R.id.wallet_home_payment_setting).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshView();
    }

    private void refreshView() {
        homeBalanceTv.setText(String.format("%.2f", User.getCurrentUser(User.class).getBalance()));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.wallet_home_forward) {
            // 充值
            ARouter.getInstance().build(Constants.AROUTER_WALLET_FIRWARD).navigation();
        } else if (id == R.id.wallet_home_recharge) {
            // 提现
        } else if (id == R.id.wallet_home_bill) {
            // 账单
            ARouter.getInstance().build(Constants.AROUTER_WALLET_BILL).navigation();
        } else if (id == R.id.wallet_home_payment_setting) {
            // 支付设置
        } else if (id == R.id.wallet_home_question) {
            // 常见问题
            ARouter.getInstance().build(Constants.AROUTER_WALLET_QUESTION).navigation();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_home;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletHomePresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }


}
