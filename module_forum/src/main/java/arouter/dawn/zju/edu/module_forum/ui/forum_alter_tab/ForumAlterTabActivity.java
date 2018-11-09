package arouter.dawn.zju.edu.module_forum.ui.forum_alter_tab;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_forum.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_FORUM_ALTER_TAB)
public class ForumAlterTabActivity extends BaseActivity {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forum_alter_tab;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
