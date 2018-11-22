package arouter.dawn.zju.edu.module_order.ui.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_order.R;
import arouter.dawn.zju.edu.module_order.config.EventBusCode;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 我的订单主页面
 */
@Route(path = baselib.config.Constants.AROUTER_ORDER_HOME)
public class OrderHomeFragment extends BaseFragment<OrderHomeContract.Presenter> implements OrderHomeContract.View{

    public static final String TAG = "OrderHomeFragment";

    ViewPager viewPager;
    TabLayout tabLayout;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_home;
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected void handleEventinMainThread(BusEvent event) {
        if (event.getTarget() != null && !event.getTarget().equals(TAG)) {
            return;
        }
        int code = event.getCode();
        if (code == EventBusCode.ORDER_LIST_REFRESH) {
            mPresenter.refresh();
        }
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new OrderHomePresenter();
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

        mPresenter.refresh();
    }

    @Override
    protected void multipleFreshView() {
        super.multipleFreshView();
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
