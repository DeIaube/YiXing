package arouter.dawn.zju.edu.module_mine.ui.feedback;


import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

import arouter.dawn.zju.edu.module_mine.R;
import baselib.App;
import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.Feedback;
import baselib.util.LogUtil;

public class FeedbackPresenter extends BasePresenter<FeedbackContract.View> implements FeedbackContract.Presenter {

    static final String TAG = "FeedbackPresenter";

    @Override
    public void submitFeedback(String title, String content) {
        if (TextUtils.isEmpty(title)) {
            mView.showMessage(App.getContext().getString(R.string.title_not_null));
            return;
        }
        if (TextUtils.isEmpty(content)) {
            mView.showMessage(App.getContext().getString(R.string.content_not_null));
            return;
        }
        final Feedback feedback = new Feedback();
        feedback.setTitle(title);
        feedback.setContent(content);
        mView.showLoading();
        feedback.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, feedback.toString());
                    mView.showMessage(App.getContext().getString(R.string.feedback_submit_succeed));
                    mView.finish();
                } else {
                    LogUtil.e(TAG, e.toString());
                    mView.showNetworkError();
                }
            }
        });
    }
}
