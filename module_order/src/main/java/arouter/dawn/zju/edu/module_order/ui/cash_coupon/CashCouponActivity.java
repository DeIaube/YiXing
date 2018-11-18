package arouter.dawn.zju.edu.module_order.ui.cash_coupon;


import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ORDER_CASH_COUPON)
public class CashCouponActivity extends BaseActivity<CashCouponContract.Presenter> implements CashCouponContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_coupon;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new CashCouponPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
