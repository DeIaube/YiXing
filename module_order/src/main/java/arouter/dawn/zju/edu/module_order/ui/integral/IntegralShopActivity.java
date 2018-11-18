package arouter.dawn.zju.edu.module_order.ui.integral;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.IntegralShopCashCouponListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ORDER_INTEGRAL_SHOP)
public class IntegralShopActivity extends BaseActivity<IntegralShopContract.Presenter> implements IntegralShopContract.View {

    RecyclerView cashCouponListView;

    private IntegralShopCashCouponListAdapter mAdapter;

    @Override
    protected void initView() {
        cashCouponListView = findViewById(R.id.cash_coupon_list_view);
        mAdapter = new IntegralShopCashCouponListAdapter(
                this, new ArrayList<CashCoupon>(), new ArrayList<CashCoupon>());
        cashCouponListView.setLayoutManager(new LinearLayoutManager(this));
        cashCouponListView.setAdapter(mAdapter);

        mPresenter.init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new IntegralShopPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void refresh(List<CashCoupon> cashCouponList, List<CashCoupon> userCashCoupons) {
        mAdapter.refresh(cashCouponList, userCashCoupons);
    }
}
