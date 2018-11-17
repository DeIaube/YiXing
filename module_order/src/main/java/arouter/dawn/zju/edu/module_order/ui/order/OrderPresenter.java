package arouter.dawn.zju.edu.module_order.ui.order;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import arouter.dawn.zju.edu.module_order.adapter.OrderPagerAdapter;
import arouter.dawn.zju.edu.module_order.config.Constants;
import arouter.dawn.zju.edu.module_order.ui.order_list.OrderListFragment;
import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.Order;
import baselib.util.LogUtil;

public class OrderPresenter extends BasePresenter<OrderContract.View> implements OrderContract.Presenter {

    private static final String TAG = "OrderPresenter";

    List<List<Order>> mOrderWithTypeList;
    private List<String> mTitles;
    private List<Fragment> mFragments;

    @SuppressLint("CheckResult")
    @Override
    public void bindViewPager(final FragmentManager fragmentManager, final ViewPager viewPager, final TabLayout tabLayout) {

        mOrderWithTypeList = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("全部");
        mTitles.add("待付款");
        mTitles.add("待评价");
        mTitles.add("已评价");
        for (int i = 0; i < mTitles.size(); i++) {
            mOrderWithTypeList.add(new ArrayList<Order>());
        }

        mFragments = new ArrayList<>();
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());
        mFragments.add(new OrderListFragment());

        OrderPagerAdapter adapter = new OrderPagerAdapter(fragmentManager, mTitles, mFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @SuppressLint("CheckResult")
    @Override
    public void refresh() {
        mView.showSwipeRefreshLayout();

        AVQuery<Order> query = Order.getQuery(Order.class);
        query.include("goods")
                .findInBackground(new FindCallback<Order>() {
            @Override
            public void done(List<Order> list, AVException e) {
                mView.hideSwipeRefreshLayout();
                if (e == null) {
                    LogUtil.i(TAG, list.toString());
                    for (List<Order> orderList : mOrderWithTypeList) {
                        orderList.clear();
                    }
                    for (Order order : list) {
                        mOrderWithTypeList.get(0).add(order);
                        int type = order.getType();
                        if (type == 1) {
                            mOrderWithTypeList.get(1).add(order);
                        } else if (type == 2) {
                            mOrderWithTypeList.get(2).add(order);
                        } else {
                            mOrderWithTypeList.get(3).add(order);
                        }
                    }
                    refreshOrderListFragment();
                } else {
                    LogUtil.e(TAG, e.toString());
                    mView.showNetworkError();
                }
            }
        });
    }

    /**
     * 通过现有数据刷新ViewPager下所有页面
     */
    private void refreshOrderListFragment() {
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment fragment = mFragments.get(i);
            if (fragment instanceof OrderListFragment) {
                OrderListFragment orderListFragment = (OrderListFragment) fragment;
                orderListFragment.refresh(mOrderWithTypeList.get(i));
            }
        }
    }

}
