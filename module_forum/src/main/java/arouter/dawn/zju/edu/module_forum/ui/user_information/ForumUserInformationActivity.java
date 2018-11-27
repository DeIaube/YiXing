package arouter.dawn.zju.edu.module_forum.ui.user_information;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_USER_INFORMATION)
public class ForumUserInformationActivity extends BaseActivity<ForumUserInformationContract.Presenter> implements ForumUserInformationContract.View {

    @Override
    protected void initView() {

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
