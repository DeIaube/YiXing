package arouter.dawn.zju.edu.module_forum.ui.list;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostListAdapter;
import baselib.base.BaseFragment;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 真正的论坛主页面
 */
@Route(path = RouteConstants.AROUTER_FORUM_FORUM_LIST)
public class ForumListFragment extends BaseFragment<ForumListContract.Presenter> implements ForumListContract.View {

    SwipeRefreshLayout forumListrefreshLayout;
    RecyclerView forumListlistView;

    @Autowired(name = RouteConstants.FORUM_LIST_TAG)
    String tag;

    private ForumPostListAdapter mAdapter;

    private int lastVisivleItem;

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

        forumListlistView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 停止滑动并且当前剩余的item小于两个的时候进行数据加载
                if(newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisivleItem + 2 >=  (recyclerView.getLayoutManager()).getItemCount()){
                    mPresenter.loadMore(tag);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 获取能看到的最后一个item的下标
                lastVisivleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });

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

        mPresenter.refresh(tag);
    }

    @Override
    public void refresh(List<ForumPost> postList) {
        mAdapter.refresh(postList);
    }

    @Override
    public void loadMore(List<ForumPost> postList) {
        mAdapter.loadMore(postList);
    }

    @Override
    public void showSwipeRefreshLayout() {
        forumListrefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshLayout() {
        forumListrefreshLayout.setRefreshing(false);
    }

}
