package arouter.dawn.zju.edu.module_forum.ui.add_post;


import android.annotation.SuppressLint;
import android.util.Log;

import com.avos.avoscloud.AVFile;

import java.util.ArrayList;
import java.util.List;

import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddPostPresenter extends BasePresenter<AddPostContract.View> implements AddPostContract.Presenter {

    String TAG = "AddPostPresenter";

    @SuppressLint("CheckResult")
    @Override
    public void submit(final String title, final String content, final List<String> images) {
        mView.showLoading();
        if (images.isEmpty()) {
            submitPost(title, content, images);
            return;
        }
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                for (String image : images) {
                    AVFile file = AVFile.withAbsoluteLocalPath(image, image);
                    file.save();
                    observableEmitter.onNext(file.getUrl());
                }
                observableEmitter.onComplete();
            }
        }).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {

                    List<String> urls = new ArrayList<>();

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        urls.add(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        LogUtil.e(TAG, e.getLocalizedMessage());
                        mView.showMessage(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e(TAG, "onComplete:" + urls.toString());
                        submitPost(title, content, urls);
                    }
                });
    }

    private void submitPost(String title, String content, List<String> urls) {
        
    }
}
