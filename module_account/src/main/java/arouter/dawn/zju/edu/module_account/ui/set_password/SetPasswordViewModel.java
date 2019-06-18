package arouter.dawn.zju.edu.module_account.ui.set_password;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;

import java.util.Date;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.util.VerificationUtil;
import baselib.App;
import baselib.base2.BaseViewModel;
import baselib.constants.RouteConstants;
import baselib.util.LogUtil;

public class SetPasswordViewModel extends BaseViewModel<SetPasswordActivity, SetPasswordRepository> {

    public MutableLiveData<String> passwordData = new MutableLiveData<>();
    public MutableLiveData<String> rePasswordData = new MutableLiveData<>();
    public MutableLiveData<String> payPasswordData = new MutableLiveData<>();
    public MutableLiveData<String> phoneNumberData = new MutableLiveData<>();

    private static final String TAG = "SetPasswordViewModel";

    public SetPasswordViewModel(@NonNull Application application, SetPasswordActivity view, SetPasswordRepository repository) {
        super(application, view, repository);
    }

    public void submit() {
        String result = VerificationUtil.checkPasswordCorrect(passwordData.getValue(), rePasswordData.getValue());
        if (!TextUtils.isEmpty(result)) {
            view.makeToast(result);
            return;
        }
        User user = new User();
        user.setUsername(phoneNumberData.getValue());
        user.setPassword(passwordData.getValue());
        user.setBirth(new Date());
        user.setPayPassword(payPasswordData.getValue());
        user.setMobilePhoneNumber(phoneNumberData.getValue());
        view.showLoading("");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                view.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "signUp");
                    view.makeToast(App.getAppalication().getString(R.string.set_password_sign_success));
                    ARouter.getInstance().build(RouteConstants.AROUTER_ACCOUNT_LOGIN).navigation();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    view.makeToast(e.getLocalizedMessage());
                }
            }
        });
    }

    public void setData(String phoneNumber, String payPassword) {
        phoneNumberData.setValue(phoneNumber);
        payPasswordData.setValue(payPassword);
    }
}
