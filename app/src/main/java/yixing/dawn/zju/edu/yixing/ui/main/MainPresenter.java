package yixing.dawn.zju.edu.yixing.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_nearby.ui.NearbyFragment;
import arouter.dawn.zju.edu.module_order.ui.order.OrderFragment;
import baselib.base.BasePresenter;
import yixing.dawn.zju.edu.yixing.ui.TestFragment;
import yixing.dawn.zju.edu.yixing.adapter.MainPagerAdapter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private List<String> titles;

    public MainPresenter() {
        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("附近");
        titles.add("论坛");
        titles.add("订单");
        titles.add("我的");
    }

    @Override
    public void bindViewPager(FragmentManager fragmentManager, final ViewPager viewPager,
                              final BottomNavigationBar bottomNavigationBar) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment());
        fragments.add(new NearbyFragment());
        fragments.add(new TestFragment());
        fragments.add(new OrderFragment());
        fragments.add(new TestFragment());
        MainPagerAdapter mPagerAdapter = new MainPagerAdapter(fragmentManager, fragments);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bottomNavigationBar.selectTab(i);
                mView.setTitle(titles.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        mView.setTitle(titles.get(0));
    }

}
