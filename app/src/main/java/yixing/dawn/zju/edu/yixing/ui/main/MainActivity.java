package yixing.dawn.zju.edu.yixing.ui.main;

import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import baselib.base.BaseActivity;
import baselib.config.Constants;
import yixing.dawn.zju.edu.yixing.R;

@Route(path = Constants.AROUTER_APP_MAIN)
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{

    ViewPager viewPager;
    BottomNavigationBar bottomNavigationView;

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);

        viewPager.setOffscreenPageLimit(4);
        // 初始化BottomNavigationBar
        bottomNavigationView.setMode(BottomNavigationBar.MODE_FIXED);

        bottomNavigationView
                .addItem(new BottomNavigationItem(R.drawable.home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.nearby, "附近"))
                .addItem(new BottomNavigationItem(R.drawable.forum, "论坛"))
                .addItem(new BottomNavigationItem(R.drawable.order, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
        // 绑定ViewPager BottomNavigationView
        mPresenter.bindViewPager(getSupportFragmentManager(), viewPager, bottomNavigationView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    public void setTitle(String title) {
        setToolbarTitle(title);
    }

}
