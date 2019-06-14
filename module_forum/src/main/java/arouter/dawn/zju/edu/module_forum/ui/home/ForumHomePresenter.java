package arouter.dawn.zju.edu.module_forum.ui.home;


import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.adapter.ForumPagerAdapter;
import arouter.dawn.zju.edu.module_forum.config.Constants;
import baselib.base.BasePresenter;
import baselib.constants.RouteConstants;
import baselib.util.SPUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class ForumHomePresenter extends BasePresenter<ForumHomeContract.View> implements ForumHomeContract.Presenter {

    String TAG = "ForumHomePresenter";

    @Override
    public void bindViewPager(FragmentManager fragmentManager, ViewPager viewPager, TabLayout tabLayout) {
        List<String> titles = getTitles();
        List<Fragment> fragments = getFragments(titles);
        ForumPagerAdapter pagerAdapter = new ForumPagerAdapter(fragmentManager, titles, fragments);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);
    }

    private List<Fragment> getFragments(List<String> titles) {
        List<Fragment> fragments = new ArrayList<>();
        for (String title : titles) {
            fragments.add((Fragment) ARouter
                    .getInstance().build(RouteConstants.AROUTER_FORUM_FORUM_LIST)
            .withString(RouteConstants.FORUM_LIST_TAG, title).navigation());
        }
        return fragments;
    }

    /**
     * 初始化标题
     */
    private List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        if (SPUtil.getBoolean(Constants.TYPE_HOME_KEY, true)) {
            titles.add(Constants.TYPE_HOME);
        }

        if (SPUtil.getBoolean(Constants.TYPE_HUMANITY_KEY, true)) {
            titles.add(Constants.TYPE_HUMANITY);
        }

        if (SPUtil.getBoolean(Constants.TYPE_INTELLECTUALITY_KEY, true)) {
            titles.add(Constants.TYPE_INTELLECTUALITY);
        }

        if (SPUtil.getBoolean(Constants.TYPE_KEISURE_KEY, true)) {
            titles.add(Constants.TYPE_KEISURE);
        }

        if (SPUtil.getBoolean(Constants.TYPE_SPORTS_KEY, true)) {
            titles.add(Constants.TYPE_SPORTS);
        }

        if (SPUtil.getBoolean(Constants.TYPE_FINANCE_KEY, true)) {
            titles.add(Constants.TYPE_FINANCE);
        }

        if (SPUtil.getBoolean(Constants.TYPE_FASHION_KEY, true)) {
            titles.add(Constants.TYPE_FASHION);
        }

        if (SPUtil.getBoolean(Constants.TYPE_EMOTION_KEY, true)) {
            titles.add(Constants.TYPE_EMOTION);
        }
        return titles;
    }
}
