package arouter.dawn.zju.edu.module_forum;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM)
public class ForumFragment extends BaseFragment<ForumContract.Presenter> implements ForumContract.View{

    TabLayout tabLayout;
    SwipeRefreshLayout refreshLayout;
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumPresenter();
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.forum_view_pager);
        tabLayout = view.findViewById(R.id.forum_tab_layout);
        refreshLayout = view.findViewById(R.id.forum_swipe_refresh_layout);

        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mPresenter.bindViewPager(getChildFragmentManager(), viewPager, tabLayout);
    }
}
