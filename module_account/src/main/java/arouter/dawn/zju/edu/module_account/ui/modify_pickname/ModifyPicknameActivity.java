package arouter.dawn.zju.edu.module_account.ui.modify_pickname;


import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ACCOUNT_MODIFY_PICKNAME)
public class ModifyPicknameActivity extends BaseActivity<ModifyPicknameContract.Presenter> implements ModifyPicknameContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_pickname;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
