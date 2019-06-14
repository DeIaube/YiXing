package arouter.dawn.zju.edu.module_forum.ui.follow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumFollowListAdapter;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 展示用户关注用户页面
 */
@Route(path = RouteConstants.AROUTER_FORUM_FORUM_FOLLOW)
public class ForumFollowActivity extends BaseActivity<ForumFollowContract.Presenter> implements ForumFollowContract.View {

    RecyclerView forumFollowListView;
    View forumFollowListEmptyView;

    private ForumFollowListAdapter mAdapter;

    @Override
    protected void initView() {
        forumFollowListView = findViewById(R.id.forum_follow_list_view);
        forumFollowListEmptyView = findViewById(R.id.forum_follow_list_empty_view);

        forumFollowListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ForumFollowListAdapter(this, new ArrayList<User>());
        forumFollowListView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.refreshFollowList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_follow;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumFollowPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void refreshFollowList(List<User> userList) {
        mAdapter.refresh(userList);
        forumFollowListEmptyView.setVisibility(userList.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
