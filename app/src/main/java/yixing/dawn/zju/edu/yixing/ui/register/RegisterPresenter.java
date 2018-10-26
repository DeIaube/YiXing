package yixing.dawn.zju.edu.yixing.ui.register;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import yixing.dawn.zju.edu.yixing.base.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void register(String phoneNumber, String code) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void getCode() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(60)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mView.updateCoundDown((int) (59 - aLong));
                    }
                });
    }
}
