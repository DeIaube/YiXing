package yixing.dawn.zju.edu.yixing.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import baselib.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager,
                           BottomNavigationBar bottomNavigationBar);
    }

}
