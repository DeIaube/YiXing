package arouter.dawn.zju.edu.module_order.ui.order;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseFragment;


public class OrderFragment extends BaseFragment<OrderContract.Presenter> implements OrderContract.View {

    ViewPager viewPager;
    TabLayout tabLayout;
    SwipeRefreshLayout refreshLayout;

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

        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setProgressViewEndTarget(false, 100);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });

        mPresenter.bindViewPager(getChildFragmentManager(), viewPager, tabLayout);
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
