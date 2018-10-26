package yixing.dawn.zju.edu.yixing.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import yixing.dawn.zju.edu.yixing.R;
import yixing.dawn.zju.edu.yixing.base.BaseActivity;
import yixing.dawn.zju.edu.yixing.constant.Constant;
import yixing.dawn.zju.edu.yixing.util.SPUtil;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R.id.login_register_tv)
    TextView registerTv;
    @BindView(R.id.login_forget_password_tv)
    TextView forgetPasswordTv;
    @BindView(R.id.login_submit)
    Button submitBtn;
    @BindView(R.id.login_account_et)
    EditText accountEt;
    @BindView(R.id.login_password_et)
    EditText passwordEt;

    @Override
    protected void initView() {
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

    @OnClick({R.id.login_submit, R.id.login_register_tv, R.id.login_forget_password_tv,
            R.id.login_weibo_btn, R.id.login_qq_btn, R.id.login_wechat_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_submit:
                mPresenter.login(accountEt.getText().toString(), passwordEt.getText().toString());
                break;
            case R.id.login_register_tv:
                // todo 跳转到注册页面
                break;
            case R.id.login_forget_password_tv:
                // todo 跳转到找回密码页面
                break;
            case R.id.login_weibo_btn:
                mPresenter.loginByWeibo();
                break;
            case R.id.login_qq_btn:
                mPresenter.loginByQQ();
                break;
            case R.id.login_wechat_btn:
                mPresenter.loginByWechat();
                break;
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
