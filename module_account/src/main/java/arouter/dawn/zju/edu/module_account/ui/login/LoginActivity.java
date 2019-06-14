package arouter.dawn.zju.edu.module_account.ui.login;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.constants.RouteConstants;
import baselib.util.SPUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户登录页面
 */
@Route(path = RouteConstants.AROUTER_ACCOUNT_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    EditText accountEt;
    EditText passwordEt;

    @Override
    protected void initView() {
        passwordEt = findViewById(R.id.login_password_et);
        accountEt = findViewById(R.id.login_account_et);
        accountEt.setText(SPUtil.getString(arouter.dawn.zju.edu.module_account.config.Constants.LOGIN_NUMBER, ""));
        passwordEt.setText(SPUtil.getString(arouter.dawn.zju.edu.module_account.config.Constants.LOGIN_PASSWORD, ""));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.tips)
                .setMessage(R.string.confirm_exit)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new LoginPresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.login_submit) {
            mPresenter.login(accountEt.getText().toString(), passwordEt.getText().toString());
        } else if (id == R.id.login_register_tv) {
            ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_REGISTER)
                    .navigation();
        } else if (id == R.id.login_forget_password_tv) {
            ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_RESET_PASSWORD).navigation();
        } else if (id == R.id.login_weibo_btn) {
            mPresenter.loginByWeibo();
        } else if (id == R.id.login_qq_btn) {
            mPresenter.loginByQQ();
        } else if (id == R.id.login_wechat_btn) {
            mPresenter.loginByWechat();
        }
    }

    @Override
    public void loginRsult(boolean result) {
        if (result) {
            ARouter.getInstance().build(RouteConstants.AROUTER_APP_MAIN).navigation();
            finish();
        }
    }
}
