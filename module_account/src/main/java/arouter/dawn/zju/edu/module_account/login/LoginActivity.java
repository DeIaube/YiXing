package arouter.dawn.zju.edu.module_account.login;

import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_account.R;
import baselib.config.Constant;
import baselib.base.BaseActivity;
import baselib.util.SPUtil;

@Route(path = Constant.AROUTER_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    EditText accountEt;
    EditText passwordEt;

    @Override
    protected void initView() {
        passwordEt = findViewById(R.id.login_password_et);
        accountEt = findViewById(R.id.login_account_et);
        accountEt.setText(SPUtil.getString(Constant.LOGIN_ACCOUNT, ""));
        passwordEt.setText(SPUtil.getString(Constant.LOGIN_PASSWORD, ""));
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
            ARouter.getInstance().build(Constant.AROUTER_REGISTER)
                    .navigation();
        } else if (id == R.id.login_forget_password_tv) {
            ARouter.getInstance().build(Constant.AROUTER_RESET_PASSWORD).navigation();
        } else if (id == R.id.login_weibo_btn) {
            mPresenter.loginByWeibo();
        } else if (id == R.id.login_qq_btn) {
            mPresenter.loginByQQ();
        } else if (id == R.id.login_wechat_btn) {
            mPresenter.loginByWechat();
        }
    }

    /**
     * 登录成功的回调
     */
    @Override
    public void loginSuccess() {
        // todo 登录成功的回调
    }

    /**
     * 登录失败的回调
     */
    @Override
    public void loginFailed() {
        // todo 登录失败的回调
    }
}
