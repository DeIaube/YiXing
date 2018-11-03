package arouter.dawn.zju.edu.module_account.ui.reset_password;

import android.annotation.SuppressLint;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVSMS;
import com.avos.avoscloud.AVSMSOption;
import com.avos.avoscloud.RequestMobileCodeCallback;

import java.util.concurrent.TimeUnit;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ResetPasswordPresenter extends BasePresenter<ResetPasswordContract.View> implements ResetPasswordContract.Presenter {

    String TAG = "ResetPasswordPresenter";

    @Override
    public void verificationCode(String phoneNumber, String code) {

    }

    @Override
    public void getCode(String phoneNumber) {
        User.requestPasswordResetBySmsCodeInBackground(phoneNumber, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (null == e) {
                    LogUtil.i(TAG, "requestPasswordReset");
                    startCountDown();
                } else {
                    mView.showMessage(e.getLocalizedMessage());
                    LogUtil.e(TAG, e.getLocalizedMessage());
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void startCountDown() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(60)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mView.updateCoundDown((int) (59 - aLong));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }
}
