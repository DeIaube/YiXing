package arouter.dawn.zju.edu.module_forum.ui.post;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.squareup.picasso.Picasso;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostImageListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM_POST)
public class ForumPostActivity extends BaseActivity<ForumPostContract.Presenter> implements ForumPostContract.View {

    @Autowired(name = Constants.FORUM_POST_POST)
    ForumPost post;

    TextView authorNameTv;
    ImageView authorPortraitIv;
    TextView postTitleTv;
    TextView postContentTv;
    Button followBtn;
    RecyclerView postImageListRv;

    private ForumPostImageListAdapter mInageListAdapter;

    @Override
    protected void initView() {
        authorNameTv = findViewById(R.id.forum_post_author_name);
        authorPortraitIv = findViewById(R.id.forum_post_author_portrait);
        postTitleTv = findViewById(R.id.post_title);
        postContentTv = findViewById(R.id.forum_post_content);
        followBtn = findViewById(R.id.forum_post_follow);
        postImageListRv = findViewById(R.id.forum_post_image_list_view);

        authorNameTv.setText(post.getAuthor().getPickName());
        Picasso.with(this).load(post.getAuthor().getPortrait()).into(authorPortraitIv);
        postTitleTv.setText(post.getTitle());
        postContentTv.setText(post.getContent());
        mInageListAdapter = new ForumPostImageListAdapter(this, post.getImageList());
        postImageListRv.setLayoutManager(new LinearLayoutManager(this));
        postImageListRv.setAdapter(mInageListAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_post;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        MenuItem collectionItem = menu.findItem(R.id.post_menu_collection);
        collectionItem.setTitle(R.string.forum_post_collection);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.post_menu_collection) {

        } else if (id == R.id.post_menu_share) {

        } else if (id == R.id.post_menu_report) {

        }
        return super.onOptionsItemSelected(item);
    }
}
