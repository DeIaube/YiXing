package arouter.dawn.zju.edu.module_forum.ui.list;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostListAdapter;
import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM_LIST)
public class ForumListFragment extends BaseFragment<ForumListContract.Presenter> implements ForumListContract.View, ForumPostListAdapter.onForumListClickListener {

    SwipeRefreshLayout forumListrefreshLayout;
    RecyclerView forumListlistView;

    @Autowired(name = Constants.FORUM_LIST_TAG)
    String tag;

    private ForumPostListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forum_list;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumListPresenter();
    }

    @Override
    protected void initView(View view) {
        forumListrefreshLayout = view.findViewById(R.id.forum_list_refresh);
        forumListlistView = view.findViewById(R.id.forum_list_view);

        forumListrefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        forumListrefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh(tag);
            }
        });

        forumListlistView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ForumPostListAdapter(getContext(), new ArrayList<ForumPost>());
        forumListlistView.setAdapter(mAdapter);
        forumListlistView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) < 10) {
                    return;
                }
                if (dy > 0) {
                    mPresenter.sendScrollUpEvent();
                } else {
                    mPresenter.sendScrollDownEvent();
                }
            }
        });

        mAdapter.setOnForumListClickListener(this);

        mPresenter.refresh(tag);
    }

    @Override
    public void refresh(List<ForumPost> postList) {
        mAdapter.refresh(postList);
    }

    @Override
    public void showSwipeRefreshLayout() {
        forumListrefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshLayout() {
        forumListrefreshLayout.setRefreshing(false);
    }

    /**
     * 点赞
     * @param post 点赞的post
     */
    @Override
    public void likePost(ForumPost post) {

    }

    /**
     * 跳转入帖子详情
     * @param post 点击的post
     */
    @Override
    public void clickPost(ForumPost post) {
        ARouter.getInstance().build(Constants.AROUTER_FORUM_FORUM_POST).
                withParcelable(Constants.FORUM_POST_POST, post).navigation();
    }
}
