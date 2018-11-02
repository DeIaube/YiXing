package arouter.dawn.zju.edu.module_mine.ui.setting;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.base.BaseActivity;

public class SettingActivity extends BaseActivity {
    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new SettingFragment()).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    protected void bindPresenter() {

    }
}
