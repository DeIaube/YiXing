package arouter.dawn.zju.edu.module_order.ui.list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 我的订单主页面
 * 展示订单列表
 */
@Route(path = Constants.AROUTER_ORDER_LIST)
public class OrderHomeListFragment extends BaseFragment<OrderHomeListContract.Presenter> implements OrderHomeListContract.View {

    RecyclerView recyclerView;

    private List<Order> orders;
    private OrderListAdapter adapter;
    private Fragment recommendFragment;

    public OrderHomeListFragment() {
        orders = new ArrayList<>();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_home_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new OrderHomeListPresenter();
    }

    public void refresh(List<Order> orders) {
        this.orders = orders;
        adapter.refresh(orders);
        if (recommendFragment == null) {
            recommendFragment = (Fragment) ARouter.getInstance().build(Constants.AROUTER_GOODS_RECOMMEND).navigation();
            getChildFragmentManager().beginTransaction().add(R.id.container, recommendFragment).commit();
        }
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
                new PayContainerFragment()
                        .show(getFragmentManager(), orderBean.getGoods().getPrice(),
                                orderBean.getGoods().getTitle(), orderBean.getGoods().getExplain()
                                , new PayCallback() {
                                    @Override
                                    public void paySuccess() {
                                        mPresenter.paySuccess(orderBean);
                                    }

                                    @Override
                                    public void payFailed(String msg) {
                                        mPresenter.payFailed(orderBean, msg);
                                    }
                                });
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
