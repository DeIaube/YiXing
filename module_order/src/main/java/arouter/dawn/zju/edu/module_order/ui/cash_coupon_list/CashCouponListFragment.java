package arouter.dawn.zju.edu.module_order.ui.cash_coupon_list;

import android.view.View;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.UserCashCoupon;
import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseFragment;

public class CashCouponListFragment extends BaseFragment<CashCouponListContract.Presenter> implements CashCouponListContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_coupon_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new CashCouponListPresenter();
    }

    @Override
    protected void initView(View view) {

    }

    public void refresh(List<UserCashCoupon> userCashCouponList) {

    }
}
