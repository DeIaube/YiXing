package arouter.dawn.zju.edu.module_order.ui.integral;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.IntegralShopCashCouponListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 积分商城页面
 * 兑换代金券
 */
@Route(path = Constants.AROUTER_ORDER_INTEGRAL_SHOP)
public class IntegralShopActivity extends BaseActivity<IntegralShopContract.Presenter> implements IntegralShopContract.View, IntegralShopCashCouponListAdapter.OnBuyClickListener {

    RecyclerView cashCouponListView;

    private IntegralShopCashCouponListAdapter mAdapter;

    @Override
    protected void initView() {
        cashCouponListView = findViewById(R.id.cash_coupon_list_view);
        mAdapter = new IntegralShopCashCouponListAdapter(
                this, new ArrayList<CashCoupon>(), new ArrayList<CashCoupon>());
        cashCouponListView.setLayoutManager(new LinearLayoutManager(this));
        cashCouponListView.setAdapter(mAdapter);
        mAdapter.setOnBuyClickListener(this);

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

    @Override
    public void buyCashCoupon(CashCoupon cashCoupon) {
        mPresenter.buyCashCoupon(cashCoupon);
    }

    @Override
    public void useCashCoupon(CashCoupon cashCoupon) {
        ARouter.getInstance().build(Constants.AROUTER_GOODS_NEARBY_ACTIVITY).navigation();
    }
}
