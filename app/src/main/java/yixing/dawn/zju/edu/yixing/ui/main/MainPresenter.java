package yixing.dawn.zju.edu.yixing.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import java.util.ArrayList;
import java.util.List;

import baselib.base.BasePresenter;
import baselib.constants.RouteConstants;
import yixing.dawn.zju.edu.yixing.adapter.MainPagerAdapter;
import yixing.dawn.zju.edu.yixing.ui.home.HomeFragment;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Override
    public void bindViewPager(FragmentManager fragmentManager, final ViewPager viewPager,
                              final BottomNavigationBar bottomNavigationBar) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add((Fragment) ARouter.getInstance().
                build(RouteConstants.AROUTER_GOODS_NEARBY_FRAGMENT).navigation());
        fragments.add((Fragment) ARouter.getInstance().
                build(RouteConstants.AROUTER_FORUM_FORUM_HOME).navigation());
        fragments.add((Fragment) ARouter.getInstance().
                build(RouteConstants.AROUTER_ORDER_HOME).navigation());
        fragments.add((Fragment) ARouter.getInstance().
                build(RouteConstants.AROUTER_SETTING_MINE).navigation());
        MainPagerAdapter mPagerAdapter = new MainPagerAdapter(fragmentManager, fragments);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bottomNavigationBar.selectTab(i);
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
    }

}
