package arouter.dawn.zju.edu.module_account.ui.modify_pickname;


import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.databinding.ActivityModifyPicknameBinding;
import baselib.base2.BaseActivity;
import baselib.constants.RouteConstants;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户更改昵称页面
 */
@Route(path = RouteConstants.AROUTER_ACCOUNT_MODIFY_PICKNAME)
public class ModifyPicknameActivity extends BaseActivity<ActivityModifyPicknameBinding, ModifyPickNameViewModel> {

    @Override
    protected void init() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        viewModel.picknameData.observe(getLifecycleOwner(), s -> {
            if (s.length() >= 5 && s.length() <= 24) {
                binding.modifyPicknameConfirmModify.setClickable(true);
                binding.modifyPicknameConfirmModify.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            } else {
                binding.modifyPicknameConfirmModify.setClickable(false);
                binding.modifyPicknameConfirmModify.setBackgroundColor(getResources().getColor(R.color.lightgrey));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

}
