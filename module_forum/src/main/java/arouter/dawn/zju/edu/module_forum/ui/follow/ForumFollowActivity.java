package arouter.dawn.zju.edu.module_forum.ui.follow;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 展示用户关注用户页面
 */
@Route(path = Constants.AROUTER_FORUM_FORUM_FOLLOW)
public class ForumFollowActivity extends BaseActivity<ForumFollowContract.Presenter> implements ForumFollowContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_follow;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ForumFollowPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}