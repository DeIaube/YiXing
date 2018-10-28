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

        OrderBean orderBean = new OrderBean();
        orderBean.setCreate_time("2018-10-28");
        orderBean.setGoods_title("守望屁股");
        orderBean.setPay(128);
        orderBean.setType("待付款");
        orderBean.setGoods_preview("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png");
        orders.add(orderBean);

        OrderBean orderBean2 = new OrderBean();
        orderBean2.setCreate_time("2018-10-28");
        orderBean2.setGoods_title("小胡子养成计划");
        orderBean2.setType("已经完成");
        orderBean2.setPay(541);
        orderBean2.setGoods_preview("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1378027402,433619994&fm=26&gp=0.jpg");
        orders.add(orderBean2);

        OrderListAdapter adapter = new OrderListAdapter(getContext(), orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
