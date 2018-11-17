package arouter.dawn.zju.edu.module_order.ui.order;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseFragment;

@Route(path = baselib.config.Constants.AROUTER_ORDER_ORDER)
public class OrderFragment extends BaseFragment<OrderContract.Presenter> implements OrderContract.View{

    ViewPager viewPager;
    TabLayout tabLayout;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new OrderPresenter();
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.order_view_pager);
        tabLayout = view.findViewById(R.id.order_tab_layout);
        refreshLayout = view.findViewById(R.id.order_refresh_layout);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("我的订单");

        viewPager.setOffscreenPageLimit(3);

        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setProgressViewEndTarget(false, 175);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });

        mPresenter.bindViewPager(getChildFragmentManager(), viewPager, tabLayout);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.refresh();
    }

    @Override
    public void showSwipeRefreshLayout() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshLayout() {
        refreshLayout.setRefreshing(false);
    }
}
