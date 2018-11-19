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
import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import arouter.dawn.zju.edu.module_pay.pay.PayBuiled;
import baselib.base.BaseFragment;
import arouter.dawn.zju.edu.lib_net.bean.order.Order;
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
            public void cancelOrderClickListener(View view, final Order orderBean) {
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.tips)
                        .setMessage(R.string.cancel_order)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.cancelOrder(orderBean);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }

            @Override
            public void payOrderClickListener(View view, final Order orderBean) {
                new PayBuiled(getActivity())
                        .setTitle(orderBean.getGoods().getTitle())
                        .setContent(orderBean.getGoods().getExplain())
                        .setPrice(orderBean.getGoods().getPrice())
                        .setPayCallback(new PayCallback() {
                            @Override
                            public void paySuccess() {
                                mPresenter.savePayInformation(orderBean);
                            }

                            @Override
                            public void payFailed() {
                                showMessage(getString(R.string.order_pay_failed));
                            }
                        })
                        .builedAliPay()
                        .pay(view);
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
            public void orderDetailClickListener(View view, Order orderBean) {

            }

            @Override
            public void orderRefundClickListener(View view, Order orderBean) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
