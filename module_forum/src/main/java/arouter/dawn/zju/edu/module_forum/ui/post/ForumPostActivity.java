package arouter.dawn.zju.edu.module_forum.ui.post;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_FORUM_POST)
public class ForumPostActivity extends BaseActivity<ForumPostContract.Presenter> implements ForumPostContract.View {

    @Autowired(name = Constants.FORUM_POST_POST)
    ForumPost post;

    @Override
    protected void initView() {
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
}
