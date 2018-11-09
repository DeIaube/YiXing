package arouter.dawn.zju.edu.module_forum.ui.add_post;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_ADD_POST)
public class AddPostActivity extends BaseActivity<AddPostContract.Presenter> implements AddPostContract.View{

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_post;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new AddPostPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
