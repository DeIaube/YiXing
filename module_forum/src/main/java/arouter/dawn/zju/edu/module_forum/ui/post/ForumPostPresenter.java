package arouter.dawn.zju.edu.module_forum.ui.post;


import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.List;

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

    private List<ForumComment> mCommentList;

    public ForumPostPresenter() {
        mCommentList = new ArrayList<>();
    }

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
                    mCommentList.add(comment);
                    mView.refreshCommentList(mCommentList);
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

    @Override
    public void initCommentList(ForumPost post) {
        AVQuery<ForumComment> commentAVQuery = ForumComment.getQuery(ForumComment.class);
        commentAVQuery.whereEqualTo("post", post)
                .include("owner")
                .findInBackground(new FindCallback<ForumComment>() {
                    @Override
                    public void done(List<ForumComment> list, AVException e) {
                        if (e == null) {
                            LogUtil.i(TAG, "initCommentList:" + list.toString());
                            if (list.isEmpty()) {
                                mView.hideCommentList();
                            } else {
                                mCommentList = list;
                                mView.showCommentList();
                                mView.refreshCommentList(mCommentList);
                            }
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                        }
                    }
                });
    }
}
