package arouter.dawn.zju.edu.module_mine.ui.setting;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 设置页面
 */
@Route(path = RouteConstants.AROUTER_SETTING_SETTING)
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
