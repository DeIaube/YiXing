package arouter.dawn.zju.edu.module_forum.ui.home;


import android.content.Intent;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.config.EventBusCode;
import baselib.base.BaseFragment;
import baselib.bus.BusEvent;
import baselib.constants.RouteConstants;

import static android.app.Activity.RESULT_OK;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 论坛主页面
 */
@Route(path = RouteConstants.AROUTER_FORUM_FORUM_HOME)
public class ForumHomeFragment extends BaseFragment<ForumHomeContract.Presenter> implements ForumHomeContract.View, View.OnClickListener {

    public static final String TAG = "ForumHomeFragment";
    static final int REQUEST_ALERT_TAB_CODE = 0x1;

    TabLayout forumTabLayou;
    ViewPager forumViewPager;
    FloatingActionButton forumAddPostFab;
    FloatingActionButton forumMyFollowFab;
    FloatingActionButton forumMyCollectionFab;
    FloatingActionMenu forumMenu;

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
        forumAddPostFab = view.findViewById(R.id.forum_home_add_post);
        forumMyFollowFab = view.findViewById(R.id.forum_home_my_follow);
        forumMyCollectionFab = view.findViewById(R.id.forum_home_my_collection);
        forumMenu = view.findViewById(R.id.forum_menu);

        forumAddPostFab.setOnClickListener(this);
        forumMyFollowFab.setOnClickListener(this);
        forumMyCollectionFab.setOnClickListener(this);
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
            forumMenu.hideMenu(true);
        } else if (code == EventBusCode.FORUM_LIST_SCROLL_DOWN) {
            forumMenu.showMenu(true);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.forum_alter_tab_layout) {
            Postcard postcard = ARouter.getInstance().build(RouteConstants.AROUTER_FORUM_ALTER_TAB);
            LogisticsCenter.completion(postcard);
            Class<?> destination = postcard.getDestination();
            Intent intent = new Intent(getContext(),destination);
            startActivityForResult(intent, REQUEST_ALERT_TAB_CODE);
        } else if (id == R.id.forum_home_add_post) {
            ARouter.getInstance().build(RouteConstants.AROUTER_FORUM_ADD_POST).navigation();
            forumMenu.close(true);
        } else if (id == R.id.forum_home_my_follow) {
            ARouter.getInstance().build(RouteConstants.AROUTER_FORUM_FORUM_FOLLOW).navigation();
            forumMenu.close(true);
        } else if (id == R.id.forum_home_my_collection) {
            ARouter.getInstance().build(RouteConstants.AROUTER_FORUM_COLLECTION).navigation();
            forumMenu.close(true);
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
