package arouter.dawn.zju.edu.module_account.ui.modify_pickname;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ACCOUNT_MODIFY_PICKNAME)
public class ModifyPicknameActivity extends BaseActivity<ModifyPicknameContract.Presenter> implements ModifyPicknameContract.View, View.OnClickListener {

    EditText modifyPicknameEt;
    Button confirmModifyBtn;
    
    @Override
    protected void initView() {
        modifyPicknameEt = findViewById(R.id.modify_pickname_pickname);
        confirmModifyBtn = findViewById(R.id.modify_pickname_confirm_modify);
        confirmModifyBtn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_pickname;
    }

    @Override
    protected void bindPresenter() {
        mPresenter = new ModifyPicknamePresenter();
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.modify_pickname_confirm_modify) {

        }
    }
}
