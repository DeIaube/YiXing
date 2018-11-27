package arouter.dawn.zju.edu.module_forum.ui.user_information;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import arouter.dawn.zju.edu.module_forum.adapter.ForumPostListAdapter;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_USER_INFORMATION)
public class ForumUserInformationActivity extends BaseActivity<ForumUserInformationContract.Presenter> implements ForumUserInformationContract.View, View.OnClickListener {

    ImageView userPortraitIv;
    TextView userPicknameTv;
    Button userFollowBtn;
    TextView userFollowCountTv;
    TextView userFollowedCountTv;
    TextView userPostCountTv;
    RecyclerView userPostListRv;

    @Autowired(name = Constants.FORUM_USER_INFORMATION_USER_ID)
    String userId;
    User user;

    private ForumPostListAdapter mAdapter;

    @Override
    protected void initView() {
        userPortraitIv = findViewById(R.id.forum_user_information_portrait);
        userPicknameTv = findViewById(R.id.forum_user_information_pickname);
        userFollowBtn = findViewById(R.id.forum_user_information_follow);
        userFollowCountTv = findViewById(R.id.forum_user_information_follow_count);
        userFollowedCountTv = findViewById(R.id.forum_user_information_followed_count);
        userPostCountTv = findViewById(R.id.forum_user_information_post_count);
        userPostListRv = findViewById(R.id.forum_user_information_post_list);

        userFollowBtn.setOnClickListener(this);

        userPostListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ForumPostListAdapter(this, new ArrayList<ForumPost>());
        userPostListRv.setAdapter(mAdapter);

        mPresenter.refresh(userId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_user_information;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumUserInformationPresenter();
    }

    @Override
    public void refreshFollowCount(int count) {
        userFollowCountTv.setText(String.valueOf(count));
    }

    @Override
    public void refreshFollowedCount(int count) {
        userFollowedCountTv.setText(String.valueOf(count));
    }

    @Override
    public void refreshPostCount(int count) {
        userPostCountTv.setText(String.valueOf(count));
    }

    @Override
    public void refreshPostList(List<ForumPost> postList) {
        mAdapter.refresh(postList);
    }

    @Override
    public void setFollowing() {
        userFollowBtn.setVisibility(View.VISIBLE);
        userFollowBtn.setBackground(getResources().getDrawable(R.drawable.forum_user_information_unfollow_bg));
        userFollowBtn.setTextColor(getResources().getColor(R.color.white));
        userFollowBtn.setText(getString(R.string.user_information_following));
    }

    @Override
    public void setUnFollow() {
        userFollowBtn.setVisibility(View.VISIBLE);
        userFollowBtn.setBackground(getResources().getDrawable(R.drawable.forum_user_information_following_bg));
        userFollowBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        userFollowBtn.setText(getString(R.string.user_information_unfollow));
    }

    @Override
    public void setFollowAble(boolean able) {
        userFollowBtn.setClickable(able);
    }

    @Override
    public void updateUser(User user) {
        this.user = user;
        Picasso.with(this).load(user.getPortrait()).into(userPortraitIv);
        userPicknameTv.setText(user.getPickName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_information_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.user_information_menu_share) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.forum_user_information_follow) {
            mPresenter.follow(user);
        }
    }
}
