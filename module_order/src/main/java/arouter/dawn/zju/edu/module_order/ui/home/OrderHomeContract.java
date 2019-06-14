package arouter.dawn.zju.edu.module_order.ui.home;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface OrderHomeContract {

    interface View extends BaseContract.BaseView {
        void showSwipeRefreshLayout();
        void hideSwipeRefreshLayout();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager,
                           TabLayout tabLayout);
        void refresh();
    }

}
