package arouter.dawn.zju.edu.module_order.ui.integral;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ORDER_INTEGRAL_SHOP)
public class IntegralShopActivity extends BaseActivity<IntegralShopContract.Presenter> implements IntegralShopContract.View {

    RecyclerView cashCouponListView;

    @Override
    protected void initView() {
        cashCouponListView = findViewById(R.id.cash_coupon_list_view);
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
}
