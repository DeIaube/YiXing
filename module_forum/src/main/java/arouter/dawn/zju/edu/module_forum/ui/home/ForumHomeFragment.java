package arouter.dawn.zju.edu.module_forum.ui.home;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM_HOME)
public class ForumHomeFragment extends BaseFragment<ForumHomeContract.Presenter> implements ForumHomeContract.View, View.OnClickListener {

    static final String TAG = "ForumHomeFragment";

    TabLayout forumTabLayou;
    ViewPager forumViewPager;
    FloatingActionButton forumAddPostFab;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumHomePresenter();
    }

    @Override
    protected void initView(View view) {
        forumViewPager = view.findViewById(R.id.forum_view_pager);
        forumTabLayou = view.findViewById(R.id.forum_tab_layout);
        forumAddPostFab = view.findViewById(R.id.forum_add_post);

        forumAddPostFab.setOnClickListener(this);
        view.findViewById(R.id.forum_alter_tab_layout).setOnClickListener(this);

        mPresenter.bindViewPager(getChildFragmentManager(), forumViewPager, forumTabLayou);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected void handleEventinMainThread(BusEvent event) {
        if (event.getTarget() != null && !event.getTarget().equals(TAG)) {
            return;
        }
        int code = event.getCode();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.forum_alter_tab_layout) {
            ARouter.getInstance().build(Constants.AROUTER_FORUM_ALTER_TAB).navigation();
        } else if (id == R.id.forum_add_post) {
            ARouter.getInstance().build(Constants.AROUTER_FORUM_ADD_POST).navigation();
            EventBus.getDefault().post(new BusEvent());
        }
    }
}
