package arouter.dawn.zju.edu.module_mine.ui.feedback;


import android.text.TextUtils;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_mine.R;
import baselib.App;
import baselib.base.BasePresenter;
import arouter.dawn.zju.edu.lib_net.bean.Feedback;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class FeedbackPresenter extends BasePresenter<FeedbackContract.View> implements FeedbackContract.Presenter {

    static final String TAG = "FeedbackPresenter";

    @Override
    public void submitFeedback(String title, String content) {
        if (TextUtils.isEmpty(title)) {
            mView.showMessage(App.getAppalication().getString(R.string.title_not_null));
            return;
        }
        if (TextUtils.isEmpty(content)) {
            mView.showMessage(App.getAppalication().getString(R.string.content_not_null));
            return;
        }
        final Feedback feedback = new Feedback();
        feedback.setTitle(title);
        feedback.setContent(content);
        feedback.setOwner(User.getCurrentUser(User.class));
        mView.showMessage(App.getAppalication().getString(R.string.feedback_submit_succeed));
        mView.finish();
        feedback.saveInBackground();
    }
}
