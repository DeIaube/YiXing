package arouter.dawn.zju.edu.module_forum.ui.forum;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import baselib.base.BaseContract;

public interface ForumContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager,
                           TabLayout tabLayout);
    }

}
