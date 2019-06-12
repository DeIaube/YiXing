package arouter.dawn.zju.edu.module_forum.ui.post;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostCommentAdapter;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostImageListAdapter;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;
import baselib.util.SPUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 帖子详情页面
 */
@Route(path = RouteConstants.AROUTER_FORUM_FORUM_POST)
public class ForumPostActivity extends BaseActivity<ForumPostContract.Presenter> implements ForumPostContract.View, View.OnClickListener {

    @Autowired(name = RouteConstants.FORUM_POST_POST)
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

    private String mCollectionMenuContent;
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
        findViewById(R.id.post_author_layout).setOnClickListener(this);

        mCollectionMenuContent = getString(R.string.forum_post_collection);

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

        mPresenter.init(post);
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem collectionItem = menu.findItem(R.id.post_menu_collection);
        collectionItem.setTitle(mCollectionMenuContent);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.post_menu_collection) {
            mPresenter.collection(post);
        } else if (id == R.id.post_menu_share) {
            // 分享文章
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, post.getContent());
            shareIntent.setType("text/plain");
            startActivity(shareIntent.createChooser(shareIntent, post.getTitle()));
        } else if (id == R.id.post_menu_report) {
           showReportDialog();
        } else if (id == R.id.post_author_layout) {
            ARouter.getInstance()
                    .build(RouteConstants.AROUTER_FORUM_USER_INFORMATION)
                    .withString(RouteConstants.FORUM_USER_INFORMATION_USER_ID, post.getAuthor().getObjectId())
                    .navigation();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 展示举报页面
     */
    private void showReportDialog() {
        View rootView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_forum_post_report, null);
        final EditText reasonEt = rootView.findViewById(R.id.forum_post_report_reason);
        final RadioButton advertisementBtn = rootView.findViewById(R.id.forum_post_report_advertisement);
        final RadioButton plagiarismBtn = rootView.findViewById(R.id.forum_post_report_plagiarism);
        final RadioButton otherBtn = rootView.findViewById(R.id.forum_post_report_other);
        new AlertDialog.Builder(this)
                .setView(rootView)
                .setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (advertisementBtn.isChecked()) {
                            mPresenter.report(post, advertisementBtn.getText().toString(),
                                    reasonEt.getText().toString());
                        } else if (plagiarismBtn.isChecked()) {
                            mPresenter.report(post, plagiarismBtn.getText().toString(),
                                    reasonEt.getText().toString());
                        } else if (otherBtn.isChecked()) {
                            mPresenter.report(post, otherBtn.getText().toString(),
                                    reasonEt.getText().toString());
                        }
                    }
                })
                .setPositiveButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.forum_post_add_comment) {
            showCommentDialog();
        } else if (id == R.id.forum_post_follow) {
            mPresenter.follow(post.getAuthor());
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
        commentDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        commentEt.setFocusable(true);
                        commentEt.setFocusableInTouchMode(true);
                        commentEt.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(commentEt,0);
                    }
                }, 250);
            }
        });
        commentDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        if(imm != null) {
                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                                    0);
                        }
                    }
                }, 250);
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

    @Override
    public void showAuthorFollowing() {
        followBtn.setText("已关注");
        followBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        followBtn.setBackground(getDrawable(R.drawable.forum_post_follow_following_bg));
    }

    @Override
    public void showAuthorUnFollow() {
        followBtn.setText("关注");
        followBtn.setTextColor(getResources().getColor(R.color.white));
        followBtn.setBackground(getDrawable(R.drawable.forum_post_follow_unfollow_bg));
    }

    @Override
    public void showPostAlreadyCollection() {
        mCollectionMenuContent = getString(R.string.forum_post_cancel_collection);
        getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
    }

    @Override
    public void showPostUnCollection() {
        mCollectionMenuContent = getString(R.string.forum_post_collection);
        getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
    }

    @Override
    public void setFollowBtnClickAble(boolean clickAble) {
        followBtn.setClickable(clickAble);
    }

    @Override
    public void setFollowBtnVisiable(boolean visiable) {
        if (visiable) {
            followBtn.setVisibility(View.VISIBLE);
        } else {
            followBtn.setVisibility(View.GONE);
        }
    }
}
