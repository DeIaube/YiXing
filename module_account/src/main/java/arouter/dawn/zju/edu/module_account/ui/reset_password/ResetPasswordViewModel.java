package arouter.dawn.zju.edu.module_account.ui.reset_password;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.UpdatePasswordCallback;

import java.util.concurrent.TimeUnit;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_account.R;
import arouter.dawn.zju.edu.module_account.util.VerificationUtil;
import baselib.App;
import baselib.base2.BaseViewModel;
import baselib.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ResetPasswordViewModel extends BaseViewModel<ResetPasswordActivity, ResetPasswordRepository> {

    private static final String TAG = "ResetPasswordViewModel";

    public MutableLiveData<String> phoneNumberData = new MutableLiveData<>();
    public MutableLiveData<String> verificationCodeData = new MutableLiveData<>();
    public MutableLiveData<String> passwordData = new MutableLiveData<>();
    public MutableLiveData<String> rePasswordData = new MutableLiveData<>();
    public MutableLiveData<Integer> verificationCodeCountDownData = new MutableLiveData<>();

    public ResetPasswordViewModel(@NonNull Application application, ResetPasswordActivity view, ResetPasswordRepository repository) {
        super(application, view, repository);
        verificationCodeCountDownData.setValue(0);
    }

    public void getVerificationCode() {
        User.requestPasswordResetBySmsCodeInBackground(phoneNumberData.getValue(), new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (null == e) {
                    LogUtil.i(TAG, "requestPasswordReset");
                    startCountDown();
                } else {
                    view.makeToast(e.getLocalizedMessage());
                    LogUtil.e(TAG, e.getLocalizedMessage());
                }
            }
        });
    }

    public void submit() {
        if (VerificationUtil.checkCodeCorrect(verificationCodeData.getValue())) {
            view.makeToast(App.getAppalication().getString(R.string.register_code_format_error));
            return;
        }
        String result = VerificationUtil.checkPasswordCorrect(passwordData.getValue(), rePasswordData.getValue());
        if (!TextUtils.isEmpty(result)) {
            view.makeToast(result);
            return;
        }
        view.showLoading("");
        User.resetPasswordBySmsCodeInBackground(verificationCodeData.getValue(), passwordData.getValue(), new UpdatePasswordCallback() {
            @Override
            public void done(AVException e) {
                view.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "resetPassword");
                    view.makeToast(App.getAppalication().getString(R.string.reset_password_success));
                    view.finish();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    view.makeToast(e.getLocalizedMessage());
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void startCountDown() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(60)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong ->
                                verificationCodeCountDownData.setValue((int) (59 - aLong)), throwable -> { });
    }
}
