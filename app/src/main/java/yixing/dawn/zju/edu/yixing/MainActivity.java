package yixing.dawn.zju.edu.yixing;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import baselib.base.BaseActivity;
import baselib.config.Constant;

@Route(path = Constant.AROUTER_MAIN)
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    ViewPager viewPager;
    BottomNavigationBar bottomNavigationView;

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        // 初始化BottomNavigationBar
        bottomNavigationView.setTabSelectedListener(this);
        bottomNavigationView.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationView.addItem(new BottomNavigationItem(R.drawable.login_weibo, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.login_qq, "附近"))
                .addItem(new BottomNavigationItem(R.drawable.login_weibo, "论坛"))
                .addItem(new BottomNavigationItem(R.drawable.login_weibo, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.login_weibo, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindPresenter() {

    }

    /**
     * @param position 跳转的下标
     */
    @Override
    public void onTabSelected(int position) {
        Log.i("tag", "onTabSelected" + String.valueOf(position));
    }

    /**
     * @param position 从position跳出
     */
    @Override
    public void onTabUnselected(int position) {
        Log.i("tag", "onTabUnselected" + String.valueOf(position));
    }

    @Override
    public void onTabReselected(int position) {
        Log.i("tag", "onTabReselected" + String.valueOf(position));
    }
}
