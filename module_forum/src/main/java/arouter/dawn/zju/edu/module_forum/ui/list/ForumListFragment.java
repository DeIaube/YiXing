package arouter.dawn.zju.edu.module_forum.ui.list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumListAdapter;
import baselib.base.BaseFragment;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM_LIST)
public class ForumListFragment extends BaseFragment<ForumListContract.Presenter> implements ForumListContract.View{

    SwipeRefreshLayout forumListrefreshLayout;
    RecyclerView forumListlistView;

    @Autowired(name = Constants.FORUM_LIST_TAG)
    String tag;

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
            }
        });

        forumListlistView.setLayoutManager(new LinearLayoutManager(getContext()));
        forumListlistView.setAdapter(new ForumListAdapter(getContext(), new ArrayList<ForumPost>()));
    }
}
