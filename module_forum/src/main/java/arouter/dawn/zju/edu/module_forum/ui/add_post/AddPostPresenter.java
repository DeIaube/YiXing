package arouter.dawn.zju.edu.module_forum.ui.add_post;


import android.annotation.SuppressLint;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.User;
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
    public void submit(final String title, final String content, final String tag, final List<String> images) {
        mView.showLoading();
        if (images.isEmpty()) {
            submitPost(title, content, tag, images);
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
                        submitPost(title, content, tag, urls);
                    }
                });
    }

    private void submitPost(String title, String content, String tag, List<String> urls) {
        ForumPost post = new ForumPost();
        post.setAuthor(User.getCurrentUser(User.class));
        post.setTitle(title);
        post.setContent(content);
        post.setImageList(urls);
        post.setTag(tag);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    mView.hideLoading();
                    mView.submitSuccess();
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }
}
