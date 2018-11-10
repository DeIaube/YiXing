package arouter.dawn.zju.edu.module_forum.ui.home;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.config.EventBusCode;
import arouter.dawn.zju.edu.module_forum.ui.alter_tab.ForumAlterTabActivity;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;
import baselib.config.Constants;

import static android.app.Activity.RESULT_OK;

@Route(path = Constants.AROUTER_FORUM_FORUM_HOME)
public class ForumHomeFragment extends BaseFragment<ForumHomeContract.Presenter> implements ForumHomeContract.View, View.OnClickListener {

    public static final String TAG = "ForumHomeFragment";
    static final int REQUEST_ALERT_TAB_CODE = 0x1;

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
        if (code == EventBusCode.FORUM_LIST_SCROLL_UP) {
            forumAddPostFab.hide();
        } else if (code == EventBusCode.FORUM_LIST_SCROLL_DOWN) {
            forumAddPostFab.show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.forum_alter_tab_layout) {
            Postcard postcard = ARouter.getInstance().build(Constants.AROUTER_FORUM_ALTER_TAB);
            LogisticsCenter.completion(postcard);
            Class<?> destination = postcard.getDestination();
            Intent intent = new Intent(getContext(),destination);
            startActivityForResult(intent, REQUEST_ALERT_TAB_CODE);
        } else if (id == R.id.forum_add_post) {
            ARouter.getInstance().build(Constants.AROUTER_FORUM_ADD_POST).navigation();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_ALERT_TAB_CODE) {
            mPresenter.bindViewPager(getChildFragmentManager(), forumViewPager, forumTabLayou);
        }
    }
}
