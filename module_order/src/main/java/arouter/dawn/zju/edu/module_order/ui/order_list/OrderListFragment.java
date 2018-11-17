package arouter.dawn.zju.edu.module_order.ui.order_list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.OrderListAdapter;
import baselib.base.BaseFragment;
import arouter.dawn.zju.edu.lib_net.bean.Order;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ORDER_LIST)
public class OrderListFragment extends BaseFragment<OrderListContract.Presenter> implements OrderListContract.View {

    RecyclerView recyclerView;

    private List<Order> orders;
    private OrderListAdapter adapter;

    public OrderListFragment() {
        orders = new ArrayList<>();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new OrderListPresenter();
    }

    public void refresh(List<Order> orders) {
        this.orders = orders;
        adapter.refresh(orders);
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.order_list_recycler_view);
        adapter = new OrderListAdapter(getContext(), orders);
        // item回调
        adapter.setmOrderListClickListener(new OrderListAdapter.OnOrderListClickListener() {
            @Override
            public void cancelOrderClickListener(View view, Order orderBean) {
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.tips)
                        .setMessage(R.string.cancel_order)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // todo 取消订单

                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }

            @Override
            public void payOrderClickListener(View view, Order orderBean) {

            }

            @Override
            public void orderEvaluateClickListener(View view, Order orderBean) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.ORDER_ORDER_LIST_ORDER, orderBean);
                ARouter.getInstance().build(Constants.AROUTER_ORDER_EVALUATE)
                        .withBundle(Constants.ORDER_ORDER_LIST_BOUNDLE, bundle)
                        .navigation();
            }

            @Override
            public void ordelDetailClickListener(View view, Order orderBean) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
