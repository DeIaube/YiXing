package arouter.dawn.zju.edu.module_order.ui.order_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.adapter.OrderListAdapter;
import baselib.base.BaseFragment;
import baselib.bean.OrderBean;

public class OrderListFragment extends BaseFragment {

    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.order_list_recycler_view);
        List<OrderBean> orders = new ArrayList<>();
        orders.add(new OrderBean());
        orders.add(new OrderBean());
        orders.add(new OrderBean());
        orders.add(new OrderBean());
        OrderListAdapter adapter = new OrderListAdapter(getContext(), orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
