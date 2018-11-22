package arouter.dawn.zju.edu.module_order.ui.home;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_order.adapter.OrderPagerAdapter;
import arouter.dawn.zju.edu.module_order.ui.list.OrderHomeListFragment;
import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.order.Order;
import baselib.util.LogUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class OrderHomePresenter extends BasePresenter<OrderHomeContract.View> implements OrderHomeContract.Presenter {

    private static final String TAG = "OrderHomePresenter";

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
        mFragments.add(new OrderHomeListFragment());
        mFragments.add(new OrderHomeListFragment());
        mFragments.add(new OrderHomeListFragment());
        mFragments.add(new OrderHomeListFragment());

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
                .whereEqualTo("owner", User.getCurrentUser(User.class))
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
            List<Order> orderList = mOrderWithTypeList.get(i);
            Collections.sort(orderList, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o2.getCreatedAt().compareTo(o1.getCreatedAt());
                }
            });
            Fragment fragment = mFragments.get(i);
            if (fragment instanceof OrderHomeListFragment) {
                OrderHomeListFragment orderListFragment = (OrderHomeListFragment) fragment;
                orderListFragment.refresh(orderList);
            }
        }
    }

}
