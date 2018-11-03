package arouter.dawn.zju.edu.module_mine.ui.notice;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_NOTICE)
public class NoticeActivity extends BaseActivity {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    protected void bindPresenter() {

    }

}
