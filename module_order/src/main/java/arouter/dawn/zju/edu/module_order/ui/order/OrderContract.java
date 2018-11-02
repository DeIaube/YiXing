package arouter.dawn.zju.edu.module_order.ui.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import baselib.base.BaseContract;

public interface OrderContract {

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
