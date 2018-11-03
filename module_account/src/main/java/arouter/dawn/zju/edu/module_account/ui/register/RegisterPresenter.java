package arouter.dawn.zju.edu.module_account.ui.register;

import android.annotation.SuppressLint;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVSMS;
import com.avos.avoscloud.AVSMSOption;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestMobileCodeCallback;

import java.util.concurrent.TimeUnit;

import baselib.App;
import baselib.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import baselib.base.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    static final String TAG = "RegisterPresenter";

    @Override
    public void verificationCode(String phoneNumber, String code) {
        mView.showLoading();
        AVSMS.verifySMSCodeInBackground(code, phoneNumber, new AVMobilePhoneVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (null == e) {
                    LogUtil.i(TAG, "verifySMSCode");
                } else {
                    mView.showMessage(e.getLocalizedMessage());
                    LogUtil.e(TAG, e.getLocalizedMessage());
                }
                mView.hideLoading();
                mView.verificationCodeCallback(e == null);
            }
        });
    }

    @Override
    public void getCode(String phoneNumber) {
        AVSMSOption option = new AVSMSOption();
        // 验证码有效时间为10分钟
        option.setTtl(10);
        option.setApplicationName(App.getContext().getString(arouter.dawn.zju.edu.lib_res.R.string.app_name));
        AVSMS.requestSMSCodeInBackground(phoneNumber, option, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (null == e) {
                    LogUtil.i(TAG, "requestSMSCode");
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
