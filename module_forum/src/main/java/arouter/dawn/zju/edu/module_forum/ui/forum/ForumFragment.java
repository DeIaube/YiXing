package arouter.dawn.zju.edu.module_forum.ui.forum;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM)
public class ForumFragment extends BaseFragment<ForumContract.Presenter> implements ForumContract.View{

    TabLayout tabLayout;
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
        mPresenter.bindViewPager(getChildFragmentManager(), viewPager, tabLayout);
    }
}
