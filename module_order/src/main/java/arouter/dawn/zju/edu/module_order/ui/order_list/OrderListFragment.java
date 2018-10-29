package arouter.dawn.zju.edu.module_order.ui.order_list;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.OrderListAdapter;
import baselib.base.BaseFragment;
import arouter.dawn.zju.edu.lib_net.bean.OrderBean;

public class OrderListFragment extends BaseFragment<OrderListContract.Presenter> implements OrderListContract.View {

    RecyclerView recyclerView;

    private List<OrderBean> orders;

    public OrderListFragment() {
        orders = new ArrayList<>();
    }

    @SuppressLint("ValidFragment")
    public OrderListFragment(List<OrderBean> orders) {
        this.orders = orders;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new OrderListPresenter();
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.order_list_recycler_view);
        OrderListAdapter adapter = new OrderListAdapter(getContext(), orders);
        // item回调
        adapter.setOnOrderListClickListener(new OrderListAdapter.OnOrderListClickListener() {
            @Override
            public void cancelOrderClickListener(View view, OrderBean orderBean) {

            }

            @Override
            public void payOrderClickListener(View view, OrderBean orderBean) {

            }

            @Override
            public void customerServicClickListener(View view, OrderBean orderBean) {

            }

            @Override
            public void ordelDetailClickListener(View view, OrderBean orderBean) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
