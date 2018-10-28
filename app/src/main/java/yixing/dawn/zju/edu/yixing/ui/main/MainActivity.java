package yixing.dawn.zju.edu.yixing.ui.main;

import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import baselib.base.BaseActivity;
import baselib.config.Constant;
import yixing.dawn.zju.edu.yixing.R;

@Route(path = Constant.AROUTER_MAIN)
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{

    ViewPager viewPager;
    BottomNavigationBar bottomNavigationView;

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.main_bottom_navigation);
        // 初始化BottomNavigationBar
        bottomNavigationView.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationView.addItem(new BottomNavigationItem(R.drawable.login_weibo, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.login_qq, "附近"))
                .addItem(new BottomNavigationItem(R.drawable.login_weibo, "论坛"))
                .addItem(new BottomNavigationItem(R.drawable.login_weibo, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.login_weibo, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
        // 绑定ViewPager BottomNavigationView
        mPresenter.bindViewPager(this, viewPager, bottomNavigationView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new MainPresenter();
    }
}
