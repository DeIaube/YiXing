package arouter.dawn.zju.edu.module_account.retrieve_password;

import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.register.RegisterPresenter;
import baselib.base.BaseActivity;
import baselib.config.Constant;

@Route(path = Constant.AROUTER_RETRIEVE_PASSWORD)
public class RetrievePasswordActivity extends BaseActivity<RegisterPresenter> implements RetrievePasswordContract.View {


    EditText passwordEt;
    EditText rePasswordEt;

    @Override
    protected void initView() {
        passwordEt = findViewById(R.id.retrieve_password_password_et);
        rePasswordEt = findViewById(R.id.retrieve_password_repassword_et);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    protected void bindPresenter() {

    }

    public void onViewClicked(View view) {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
