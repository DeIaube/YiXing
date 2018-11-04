package arouter.dawn.zju.edu.module_account.ui.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import baselib.base.BaseActivity;
import baselib.config.Constants;

@Route(path = Constants.AROUTER_ACCOUNT_PERSONAL)
public class PersonalActivity extends BaseActivity implements View.OnClickListener {

    TextView personalUsernameTv;
    TextView personalPhoneNumberTv;
    ImageView personalPortraitIv;

    @Override
    protected void initView() {
        personalUsernameTv = findViewById(R.id.personal_username);
        personalPhoneNumberTv = findViewById(R.id.personal_bind_phone_number);
        personalPortraitIv = findViewById(R.id.personal_portrait);

        findViewById(R.id.check_portrait).setOnClickListener(this);
        findViewById(R.id.check_username).setOnClickListener(this);

        User user = User.getCurrentUser(User.class);
        personalUsernameTv.setText(user.getName());
        personalPhoneNumberTv.setText(user.getMobilePhoneNumber());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.check_portrait) {
            // 选取头像
        } else if (id == R.id.check_username) {
            // 点击用户昵称
        }
    }
}
