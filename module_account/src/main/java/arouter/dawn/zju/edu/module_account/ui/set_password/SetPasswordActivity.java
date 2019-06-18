package arouter.dawn.zju.edu.module_account.ui.set_password;

import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.databinding.ActivitySetPasswordBinding;
import baselib.base2.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户设置密码页面
 */
@Route(path = RouteConstants.AROUTER_ACCOUNT_SET_PASSWORD)
public class SetPasswordActivity extends BaseActivity<ActivitySetPasswordBinding, SetPasswordViewModel> {

    @Autowired(name = RouteConstants.ACCOUNT_PHONE_NUMBER)
    String phoneNumber;
    @Autowired(name = RouteConstants.ACCOUNT_PAY_PASSWORD)
    String payPassword;

    @Override
    protected void init() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel.setData(phoneNumber, payPassword);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

}
