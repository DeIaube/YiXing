package arouter.dawn.zju.edu.module_forum.ui.collection;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_COLLECTION)
public class ForumCollectionActivity extends BaseActivity<ForumCollectionContract.Presenter> implements ForumCollectionContract.View {

    RecyclerView collectionListView;

    private ForumPostListAdapter mPostListAdapter;

    @Override
    protected void initView() {
        collectionListView = findViewById(R.id.forum_collection_list_view);
        mPostListAdapter = new ForumPostListAdapter(this, new ArrayList<ForumPost>());
        collectionListView.setLayoutManager(new LinearLayoutManager(this));
        collectionListView.setAdapter(mPostListAdapter);

        mPresenter.refreshCollectionPostList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_collection;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumCollectionPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void refreshCollectionPostList(List<ForumPost> postList) {
        mPostListAdapter.refresh(postList);
    }
}
