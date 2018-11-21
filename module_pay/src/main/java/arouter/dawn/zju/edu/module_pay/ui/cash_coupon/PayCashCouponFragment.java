package arouter.dawn.zju.edu.module_pay.ui.cash_coupon;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.order.CashCoupon;
import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_pay.R;
import arouter.dawn.zju.edu.module_pay.adapter.PayCashCouponListAdapter;
import arouter.dawn.zju.edu.module_pay.config.Constants;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;

@SuppressLint("ValidFragment")
public class PayCashCouponFragment extends BaseFragment<PayCashCouponContract.Presenter> implements PayCashCouponContract.View, PayCashCouponListAdapter.OnCashCouponClickListener, View.OnClickListener {

    RecyclerView payCashCouponListView;

    private PayCashCouponListAdapter mAdapter;
    private List<CashCoupon> mCashCouponList;

    private double price;

    @SuppressLint("ValidFragment")
    public PayCashCouponFragment(double price) {
        this.price = price;
        refreshCashCouponList();
    }

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
        view.findViewById(R.id.pay_cash_coupon_dont_use_cash_coupon).setOnClickListener(this);
        if (mCashCouponList == null) {
            mCashCouponList = new ArrayList<>();
        }
        mAdapter = new PayCashCouponListAdapter(mCashCouponList, getContext());
        payCashCouponListView.setLayoutManager(new LinearLayoutManager(getContext()));
        payCashCouponListView.setAdapter(mAdapter);
        mAdapter.setOnCashCouponClickListener(this);

    }

    private void refresh() {
        if(mAdapter != null) {
            mAdapter.refresh(mCashCouponList);
        }
    }

    @Override
    public void selectCashCoupon(View v, CashCoupon cashCoupon) {
        mPresenter.selectCashCoupon(cashCoupon, price);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.pay_cash_coupon_dont_use_cash_coupon) {
            BusEvent busEvent = new BusEvent();
            busEvent.setCode(Constants.EVENT_SELETED_CASH_COUPON);
            busEvent.setTarget(PayContainerFragment.TAG);
            busEvent.setData(null);
            EventBus.getDefault().post(busEvent);
        }
    }

    public void refreshCashCouponList() {
        AVQuery<UserCashCoupon> userCashCouponAVQuery = UserCashCoupon.getQuery(UserCashCoupon.class);
        userCashCouponAVQuery
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .include("cashCoupon")
                .findInBackground(new FindCallback<UserCashCoupon>() {
                    @Override
                    public void done(List<UserCashCoupon> list, AVException e) {
                        if (e == null) {
                            List<CashCoupon> cashCouponList = new ArrayList<>();
                            for (UserCashCoupon userCashCoupon : list) {
                                cashCouponList.add(userCashCoupon.getCashCoupon());
                            }
                            mCashCouponList = cashCouponList;
                            refresh();
                        }
                    }
                });
    }
}
