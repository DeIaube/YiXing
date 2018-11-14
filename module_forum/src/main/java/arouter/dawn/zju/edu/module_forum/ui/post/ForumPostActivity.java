package arouter.dawn.zju.edu.module_forum.ui.post;

import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostCommentAdapter;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostImageListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;
import baselib.util.SPUtil;

@Route(path = Constants.AROUTER_FORUM_FORUM_POST)
public class ForumPostActivity extends BaseActivity<ForumPostContract.Presenter> implements ForumPostContract.View, View.OnClickListener {

    @Autowired(name = Constants.FORUM_POST_POST)
    ForumPost post;

    TextView authorNameTv;
    ImageView authorPortraitIv;
    TextView postTitleTv;
    TextView postContentTv;
    Button followBtn;
    RecyclerView postImageListRv;
    RecyclerView postCommentListRv;
    TextView postCommentEmptyTipTv;
    BottomSheetDialog commentDialog;
    Button commentBtn;
    EditText commentEt;

    private ForumPostCommentAdapter mCommentAdapter;

    @Override
    protected void initView() {
        authorNameTv = findViewById(R.id.forum_post_author_name);
        authorPortraitIv = findViewById(R.id.forum_post_author_portrait);
        postTitleTv = findViewById(R.id.post_title);
        postContentTv = findViewById(R.id.forum_post_content);
        followBtn = findViewById(R.id.forum_post_follow);
        postImageListRv = findViewById(R.id.forum_post_image_list_view);
        postCommentEmptyTipTv = findViewById(R.id.forum_post_comment_empty_tip);
        postCommentListRv = findViewById(R.id.forum_post_comment_list);

        initCommentDialog();

        followBtn.setOnClickListener(this);
        findViewById(R.id.forum_post_add_comment).setOnClickListener(this);

        authorNameTv.setText(post.getAuthor().getPickName());
        Picasso.with(this).load(post.getAuthor().getPortrait()).into(authorPortraitIv);
        postTitleTv.setText(post.getTitle());
        postContentTv.setText(post.getContent());
        ForumPostImageListAdapter imageListAdapter = new ForumPostImageListAdapter(this, post.getImageList());
        postImageListRv.setLayoutManager(new LinearLayoutManager(this));
        postImageListRv.setAdapter(imageListAdapter);

        postCommentListRv.setLayoutManager(new LinearLayoutManager(this));
        mCommentAdapter = new ForumPostCommentAdapter(this, new ArrayList<ForumComment>());
        postCommentListRv.setAdapter(mCommentAdapter);

        mPresenter.initCommentList(post);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_post;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumPostPresenter();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.forum_post_add_comment) {
            showCommentDialog();
        } else if (id == R.id.forum_post_follow) {

        }
    }

    private void initCommentDialog() {
        commentDialog = new BottomSheetDialog(this);
        View rootView = LayoutInflater.from(this).inflate(R.layout.dialog_forum_post_comment, null);
        commentEt = rootView.findViewById(R.id.comment_content);
        commentBtn = rootView.findViewById(R.id.comment_submit);
        commentDialog.setContentView(rootView);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.comment(post, commentEt.getText().toString());
            }
        });
        commentDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mPresenter.cancelComment(commentEt.getText().toString());
            }
        });
    }

    /**
     * 弹出评论页面
     */
    private void showCommentDialog() {
        String comment = SPUtil.getString(arouter.dawn.zju.edu.module_forum.config.Constants.LAST_COMMENT, "");
        commentEt.setText(comment);
        commentDialog.show();
    }

    @Override
    public void hideCommentDialog() {
        commentDialog.hide();
    }

    @Override
    public void refreshCommentList(List<ForumComment> commentList) {
        mCommentAdapter.refresh(commentList);
    }

    @Override
    public void showCommentList() {
        postCommentEmptyTipTv.setVisibility(View.GONE);
        postCommentListRv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCommentList() {
        postCommentListRv.setVisibility(View.GONE);
        postCommentEmptyTipTv.setVisibility(View.VISIBLE);
    }
}
