package arouter.dawn.zju.edu.module_order.ui.cash_coupon_list;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import arouter.dawn.zju.edu.lib_net.bean.order.UserCashCoupon;
import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.CashCouponListAdapter;
import baselib.base.BaseFragment;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 我的代金券列表页面
 */
public class CashCouponListFragment extends BaseFragment<CashCouponListContract.Presenter> implements CashCouponListContract.View, View.OnClickListener {

    RecyclerView cashCouponListView;

    Button cashCouponGetCashCouponBtn;

    private CashCouponListAdapter mAdapter;

    @Override
    protected void initView(View view) {
        cashCouponListView = view.findViewById(R.id.cash_coupon_list_view);
        cashCouponGetCashCouponBtn = view.findViewById(R.id.cash_coupon_get_cash_coupon);

        cashCouponGetCashCouponBtn.setOnClickListener(this);

        mAdapter = new CashCouponListAdapter(getContext(), new ArrayList<UserCashCoupon>());
        cashCouponListView.setLayoutManager(new LinearLayoutManager(getContext()));
        cashCouponListView.setAdapter(mAdapter);
        mAdapter.setCashCouponListClickListener(new CashCouponListAdapter.CashCouponListClickListener() {
            @Override
            public void CashCouponClick(View v) {
                new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                        .setTitle(R.string.tips)
                        .setMessage(R.string.cash_coupon_infamation)
                        .setNegativeButton(R.string.confirm, null)
                        .show();
            }
        });
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cash_coupon_get_cash_coupon) {
            ARouter.getInstance().build(RouteConstants.AROUTER_ORDER_INTEGRAL_SHOP).navigation();
        }
    }
}
