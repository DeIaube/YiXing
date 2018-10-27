package arouter.dawn.zju.edu.module_account.retrieve_password;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.register.RegisterPresenter;
import baselib.base.BaseActivity;
import baselib.config.Constant;

@Route(path = Constant.AROUTER_RETRIEVE_PASSWORD)
public class RetrievePasswordActivity extends BaseActivity<RegisterPresenter> implements RetrievePasswordContract.View {

    @Override
    protected void initView() {

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
}
