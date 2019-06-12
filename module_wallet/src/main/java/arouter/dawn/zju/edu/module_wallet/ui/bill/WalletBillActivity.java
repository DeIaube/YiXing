package arouter.dawn.zju.edu.module_wallet.ui.bill;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_wallet.R;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 我的账单页面
 */
@Route(path = RouteConstants.AROUTER_WALLET_BILL)
public class WalletBillActivity extends BaseActivity<WalletBillContract.Presenter> implements
        WalletBillContract.View {

    ViewPager walletBillViewPager;
    TabLayout walletBillTabLayout;

    @Override
    protected void initView() {
        walletBillViewPager = findViewById(R.id.wallet_bill_view_pager);
        walletBillTabLayout = findViewById(R.id.wallet_bill_tab_layout);

        mPresenter.bindViewPager(getSupportFragmentManager(), walletBillViewPager, walletBillTabLayout);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_bill;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new WalletBillPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
