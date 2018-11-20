package arouter.dawn.zju.edu.module_pay.ui.cash_coupon;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.adapter.PayCashCouponListAdapter;
import arouter.dawn.zju.edu.module_pay.config.Constants;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;

public class PayCashCouponFragment extends BaseFragment<PayCashCouponContract.Presenter> implements PayCashCouponContract.View, PayCashCouponListAdapter.OnCashCouponClickListener {

    RecyclerView payCashCouponListView;

    private PayCashCouponListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pay_cash_coupon;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new PayCashCouponPresenter();
    }

    @Override
    protected void initView(View view) {
        payCashCouponListView = view.findViewById(R.id.pay_cash_coupon_list_view);
        mAdapter = new PayCashCouponListAdapter(new ArrayList<CashCoupon>(), getContext());
        payCashCouponListView.setLayoutManager(new LinearLayoutManager(getContext()));
        payCashCouponListView.setAdapter(mAdapter);
        mAdapter.setOnCashCouponClickListener(this);

        mPresenter.refreshCashCouponList();
    }

    @Override
    public void refreshCashCouponList(List<CashCoupon> cashCouponList) {
        mAdapter.refresh(cashCouponList);
    }

    @Override
    public void selectCashCoupon(View v, CashCoupon cashCoupon) {
        BusEvent busEvent = new BusEvent();
        busEvent.setCode(Constants.EVENT_SELETED_CASH_COUPON);
        busEvent.setTarget(PayContainerFragment.TAG);
        busEvent.setData(cashCoupon);
        EventBus.getDefault().post(busEvent);
    }
}
