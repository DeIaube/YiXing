package arouter.dawn.zju.edu.module_forum.ui.forum;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.module_forum.adapter.ForumPagerAdapter;
import arouter.dawn.zju.edu.module_forum.config.Constants;
import arouter.dawn.zju.edu.module_forum.ui.forum_list.ForumListFragment;
import baselib.base.BasePresenter;
import baselib.util.SPUtil;

public class ForumPresenter extends BasePresenter<ForumContract.View> implements ForumContract.Presenter {

    String TAG = "ForumPresenter";

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
            fragments.add(new ForumListFragment());
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
