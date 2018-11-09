package arouter.dawn.zju.edu.module_forum.ui.forum;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM)
public class ForumFragment extends BaseFragment<ForumContract.Presenter> implements ForumContract.View, View.OnClickListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton addArticleFab;

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
        addArticleFab = view.findViewById(R.id.forum_add_article);

        addArticleFab.setOnClickListener(this);
        view.findViewById(R.id.forum_alter_tab_layout).setOnClickListener(this);

        mPresenter.bindViewPager(getChildFragmentManager(), viewPager, tabLayout);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.forum_alter_tab_layout) {
            ARouter.getInstance().build(Constants.AROUTER_FORUM_ALTER_TAB).navigation();
        } else if (id == R.id.forum_add_article) {
            
        }
    }
}
