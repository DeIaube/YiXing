package arouter.dawn.zju.edu.module_order.ui.list;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.OrderListAdapter;
import arouter.dawn.zju.edu.module_pay.callback.PayCallback;
import arouter.dawn.zju.edu.module_pay.ui.container.PayContainerFragment;
import baselib.base.BaseFragment;
import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import baselib.config.Constants;
import baselib.util.ZXingUtils;

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
    View emptyLayout;

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
        if (orders.isEmpty()) {
            emptyLayout.setVisibility(View.VISIBLE);
        } else {
            emptyLayout.setVisibility(View.GONE);
        }
        if (recommendFragment == null) {
            recommendFragment = (Fragment) ARouter.getInstance().build(Constants.AROUTER_GOODS_RECOMMEND).navigation();
            getChildFragmentManager().beginTransaction().add(R.id.container, recommendFragment).commit();
        }
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.order_list_recycler_view);
        emptyLayout = view.findViewById(R.id.order_list_empty_view);
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
                if (orderBean.getType() == 2 || orderBean.getType() == 3) {
                    ImageView iv = new ImageView(getContext());
                    iv.setPadding(20,40,20,40);
                    iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.er));
                    new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                            .setTitle("活动二维码")
                            .setView(iv)
                            .setPositiveButton("确定", null)
                            .show();
                }
            }

            @Override
            public void orderRefundClickListener(View view, Order orderBean) {

            }

            @Override
            public void showGoodsExplainClickListener(View view, Order orderBean) {
                new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                        .setTitle(R.string.order_home_list_explain)
                        .setMessage(orderBean.getGoods().getExplain())
                        .setNegativeButton(R.string.confirm, null)
                        .show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
