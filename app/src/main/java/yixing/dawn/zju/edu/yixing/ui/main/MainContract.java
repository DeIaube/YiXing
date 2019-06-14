package yixing.dawn.zju.edu.yixing.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface MainContract {

    interface View extends BaseContract.BaseView {
        void setTitle(String title);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager,
                           BottomNavigationBar bottomNavigationBar);
    }

}
