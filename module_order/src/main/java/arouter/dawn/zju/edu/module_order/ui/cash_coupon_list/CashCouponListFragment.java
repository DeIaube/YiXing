package arouter.dawn.zju.edu.module_order.ui.cash_coupon_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.CashCouponListAdapter;
import baselib.base.BaseFragment;

public class CashCouponListFragment extends BaseFragment<CashCouponListContract.Presenter> implements CashCouponListContract.View {

    RecyclerView cashCouponListView;

    private CashCouponListAdapter mAdapter;

    @Override
    protected void initView(View view) {
        cashCouponListView = view.findViewById(R.id.cash_coupon_list_view);
        mAdapter = new CashCouponListAdapter(getContext(), new ArrayList<UserCashCoupon>());
        cashCouponListView.setLayoutManager(new LinearLayoutManager(getContext()));
        cashCouponListView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_coupon_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new CashCouponListPresenter();
    }

    public void refresh(List<UserCashCoupon> userCashCouponList) {
        mAdapter.refresh(userCashCouponList);
    }
}
