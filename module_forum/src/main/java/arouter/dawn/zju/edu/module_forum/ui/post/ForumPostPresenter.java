package arouter.dawn.zju.edu.module_forum.ui.post;


import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import arouter.dawn.zju.edu.lib_net.bean.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_forum.R;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import baselib.util.SPUtil;

public class ForumPostPresenter extends BasePresenter<ForumPostContract.View> implements ForumPostContract.Presenter {

    String TAG = "ForumPostPresenter";

    @Override
    public void comment(ForumPost post, String content) {
        if (TextUtils.isEmpty(content)) {
            mView.showMessage(App.getContext().getString(R.string.forum_post_comment_not_null));
            return;
        }
        final ForumComment comment = new ForumComment();
        comment.setConent(content);
        comment.setOwner(AVUser.getCurrentUser(User.class));
        comment.setPost(post);
        mView.showLoading();
        comment.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                mView.hideLoading();
                if (e == null) {
                    LogUtil.i(TAG, "comment:" + comment.toString());
                    mView.hideCommentDialog();
                    mView.showMessage(App.getContext().getString(R.string.forum_post_comment_success));
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });

    }

    @Override
    public void cancelComment(String comment) {
        if (!TextUtils.isEmpty(comment)) {
            SPUtil.put(arouter.dawn.zju.edu.module_forum.config.Constants.LAST_COMMENT, comment);
        }
    }
}
