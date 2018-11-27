package arouter.dawn.zju.edu.module_forum.ui.user_information;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_USER_INFORMATION)
public class ForumUserInformationActivity extends BaseActivity<ForumUserInformationContract.Presenter> implements ForumUserInformationContract.View {

    ImageView userPortraitIv;
    TextView userPicknameTv;
    Button userFollowBtn;
    TextView userFollowCountTv;
    TextView userFollowedCountTv;
    TextView userPostCountTv;
    RecyclerView userPostListRv;

    @Override
    protected void initView() {
        userPortraitIv = findViewById(R.id.forum_user_information_portrait);
        userPicknameTv = findViewById(R.id.forum_user_information_pickname);
        userFollowBtn = findViewById(R.id.forum_user_information_follow);
        userFollowCountTv = findViewById(R.id.forum_user_information_follow_count);
        userFollowedCountTv = findViewById(R.id.forum_user_information_followed_count);
        userPostCountTv = findViewById(R.id.forum_user_information_post_count);
        userPostListRv = findViewById(R.id.forum_user_information_post_list);

        userPostListRv.setLayoutManager(new LinearLayoutManager(this));
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

}
