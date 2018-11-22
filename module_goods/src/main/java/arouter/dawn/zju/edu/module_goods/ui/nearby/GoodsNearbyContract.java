package arouter.dawn.zju.edu.module_goods.ui.nearby;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface GoodsNearbyContract {

    interface View extends BaseContract.BaseView {
        void showSwipeRefreshLayout();
        void hideSwipeRefreshLayout();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager,
                           TabLayout tabLayout, String location);
        void refresh(String location);
        void checkoutSortGoods(int sortType);
    }

}
