package arouter.dawn.zju.edu.module_account.ui.login;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.databinding.ActivityLoginBinding;
import baselib.base2.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户登录页面
 */
@Route(path = RouteConstants.AROUTER_ACCOUNT_LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected void init() {
        viewModel.loadData();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.tips)
                .setMessage(R.string.confirm_exit)
                .setPositiveButton(R.string.confirm, (dialog, which) -> finish())
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

}
