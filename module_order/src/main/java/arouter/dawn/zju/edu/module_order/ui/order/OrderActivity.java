package arouter.dawn.zju.edu.module_order.ui.order;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_order.R;
import baselib.base.BaseActivity;

@Route(path = baselib.config.Constants.AROUTER_ORDER_ORDER)
public class OrderActivity extends BaseActivity<OrderContract.Presenter> implements OrderContract.View {

    ViewPager viewPager;
    TabLayout tabLayout;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolbar;

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.order_view_pager);
        tabLayout = findViewById(R.id.order_tab_layout);
        refreshLayout = findViewById(R.id.order_refresh_layout);
        toolbar = findViewById(R.id.toolbar);

        viewPager.setOffscreenPageLimit(3);

        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setProgressViewEndTarget(false, 175);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });

        mPresenter.bindViewPager(getSupportFragmentManager(), viewPager, tabLayout);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new OrderPresenter();
    }

    @Override
    public void showSwipeRefreshLayout() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshLayout() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
