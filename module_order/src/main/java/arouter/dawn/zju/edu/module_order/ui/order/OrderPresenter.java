package arouter.dawn.zju.edu.module_order.ui.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_order.adapter.OrderPagerAdapter;
import arouter.dawn.zju.edu.module_order.ui.order_list.OrderListFragment;
import baselib.base.BasePresenter;

public class OrderPresenter extends BasePresenter<OrderContract.View> implements OrderContract.Presenter, ViewPager.OnPageChangeListener {

    private int mCurrentIndex;

    @Override
    public void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager, TabLayout tabLayout) {
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("已取消");
        titles.add("已完成");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OrderListFragment());
        fragments.add(new OrderListFragment());
        fragments.add(new OrderListFragment());
        fragments.add(new OrderListFragment());

        OrderPagerAdapter adapter = new OrderPagerAdapter(fragmentManager, titles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void refresh() {
        mView.showSwipeRefreshLayout();
        // todo 刷新订单情况
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mCurrentIndex = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
