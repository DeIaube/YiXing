package arouter.dawn.zju.edu.module_forum.ui.collection;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostListAdapter;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 展示用户收藏帖子页面
 */
@Route(path = RouteConstants.AROUTER_FORUM_COLLECTION)
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
