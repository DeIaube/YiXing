package yixing.dawn.zju.edu.yixing.ui.main;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import baselib.base2.BaseActivity;
import baselib.constants.RouteConstants;
import yixing.dawn.zju.edu.yixing.R;
import yixing.dawn.zju.edu.yixing.adapter.MainPagerAdapter;
import yixing.dawn.zju.edu.yixing.databinding.ActivityMainBinding;
import yixing.dawn.zju.edu.yixing.ui.home.HomeFragment;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 主页面 控制五个不同module的Fragment
 * 通过ARouter获取Fragment
 */
@Route(path = RouteConstants.AROUTER_APP_MAIN)
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void init() {
        binding.mainViewPager.setOffscreenPageLimit(4);
        // 初始化BottomNavigationBar
        binding.mainBottomNavigation.setMode(BottomNavigationBar.MODE_FIXED);

        binding.mainBottomNavigation
                .addItem(new BottomNavigationItem(R.drawable.home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.nearby, "附近"))
                .addItem(new BottomNavigationItem(R.drawable.forum, "论坛"))
                .addItem(new BottomNavigationItem(R.drawable.order, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();

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
        MainPagerAdapter mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        binding.mainViewPager.setAdapter(mPagerAdapter);
        binding.mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                binding.mainBottomNavigation.selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        binding.mainBottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                binding.mainViewPager.setCurrentItem(position);
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
