package arouter.dawn.zju.edu.module_mine.ui.setting;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_SETTING_SETTING)
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
