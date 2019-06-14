package arouter.dawn.zju.edu.module_forum.ui.post;


import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumCollection;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumFollow;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPostReport;
import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.module_forum.R;
import baselib.App;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;
import baselib.util.SPUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class ForumPostPresenter extends BasePresenter<ForumPostContract.View> implements ForumPostContract.Presenter {

    String TAG = "ForumPostPresenter";

    private List<ForumComment> mCommentList;
    private ForumFollow mForumFollow;
    private ForumCollection mForumCollection;

    public ForumPostPresenter() {
        mCommentList = new ArrayList<>();
    }

    @Override
    public void comment(ForumPost post, String content) {
        if (TextUtils.isEmpty(content)) {
            mView.showMessage(App.getAppalication().getString(R.string.forum_post_comment_not_null));
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
                    mView.showMessage(App.getAppalication().getString(R.string.forum_post_comment_success));
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
    public void init(ForumPost post) {
        initForumCommentList(post);
        initForumFollowState(post);
        initForumCollection(post);
    }

    /**
     * 初始化关注帖子
     * @param post 帖子
     */
    private void initForumCollection(ForumPost post) {
        AVQuery<ForumCollection> forumCollectionAVQuery = ForumCollection.getQuery(ForumCollection.class);
        forumCollectionAVQuery.whereEqualTo("post", post)
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .getFirstInBackground(new GetCallback<ForumCollection>() {
                    @Override
                    public void done(ForumCollection forumCollection, AVException e) {
                        if (e == null) {
                            mForumCollection = forumCollection;
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                        }
                        if (mForumCollection == null) {
                            mView.showPostUnCollection();
                        } else {
                            mView.showPostAlreadyCollection();
                        }
                    }
                });
    }

    /**
     * 初始化关注状态
     */
    private void initForumFollowState(ForumPost post) {
        User user = User.getCurrentUser(User.class);
        User follower = post.getAuthor();
        if (follower.equals(user)) {
            return;
        }
        AVQuery<ForumFollow> followAVQuery = ForumFollow.getQuery(ForumFollow.class);
        followAVQuery.whereEqualTo("owner", user)
                .whereEqualTo("follower", follower)
                .findInBackground(new FindCallback<ForumFollow>() {
                    @Override
                    public void done(List<ForumFollow> list, AVException e) {
                        if (e == null) {
                            mView.setFollowBtnVisiable(true);
                            if (!list.isEmpty()) {
                                mForumFollow = list.get(0);
                            }
                            if (mForumFollow == null) {
                                mView.showAuthorUnFollow();
                            } else {
                                mView.showAuthorFollowing();
                            }
                        } else {
                            LogUtil.e(TAG, e.getLocalizedMessage());
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }
                });
    }

    /**
     * 初始化评论列表
     * @param post 帖子
     */
    private void initForumCommentList(ForumPost post) {
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

    @Override
    public void follow(User follower) {
        mView.setFollowBtnClickAble(false);
        if (mForumFollow == null) {
            mForumFollow = new ForumFollow();
            mForumFollow.setOwner(User.getCurrentUser(User.class));
            mForumFollow.setFollow(follower);
            mForumFollow.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    mView.setFollowBtnClickAble(true);
                    if (e == null) {
                        LogUtil.i(TAG, "follow");
                        mView.showAuthorFollowing();
                    } else {
                        LogUtil.e(TAG, e.getLocalizedMessage());
                        mView.showMessage(e.getLocalizedMessage());
                    }
                }
            });
        } else {
            mForumFollow.deleteInBackground(new DeleteCallback() {
                @Override
                public void done(AVException e) {
                    mView.setFollowBtnClickAble(true);
                    if (e == null) {
                        LogUtil.i(TAG, "unfollow");
                        mForumFollow = null;
                        mView.showAuthorUnFollow();
                    } else {
                        LogUtil.e(TAG, e.getLocalizedMessage());
                        mView.showMessage(e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    @Override
    public void collection(ForumPost post) {
        if (mForumCollection == null) {
            mForumCollection = new ForumCollection();
            mForumCollection.setOwner(User.getCurrentUser(User.class));
            mForumCollection.setPost(post);
            mForumCollection.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        mView.showPostAlreadyCollection();
                    } else {
                        LogUtil.e(TAG, e.getLocalizedMessage());
                    }
                }
            });
        } else {
            mForumCollection.deleteInBackground(new DeleteCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        mForumCollection = null;
                        mView.showPostUnCollection();
                    } else {
                        LogUtil.e(TAG, e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    @Override
    public void report(ForumPost post, String type, String content) {
        if (type.equals(App.getAppalication().getString(R.string.forum_post_report_other)) &&
                TextUtils.isEmpty(content)) {
            mView.showMessage(App.getAppalication().getString(R.string.forum_post_report_content_not_null));
            return;
        }
        ForumPostReport report = new ForumPostReport();
        report.setOwner(User.getCurrentUser(User.class));
        report.setContent(content);
        report.setPost(post);
        report.setType(type);
        report.saveInBackground();
        mView.showMessage(App.getAppalication().getString(R.string.forum_post_report_success));
    }
}
