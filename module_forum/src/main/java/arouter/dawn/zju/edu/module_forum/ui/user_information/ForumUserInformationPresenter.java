package arouter.dawn.zju.edu.module_forum.ui.user_information;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumFollow;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import baselib.base.BasePresenter;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class ForumUserInformationPresenter extends BasePresenter<ForumUserInformationContract.View> implements ForumUserInformationContract.Presenter {

    String TAG = "ForumUserInformationPresenter";

    private ForumFollow mForumFollow;

    @Override
    public void refresh(final String userId) {
        AVQuery<User> userAVQuery = User.getQuery(User.class);
        userAVQuery
                .whereEqualTo("objectId", userId)
                .getFirstInBackground(new GetCallback<User>() {
                    @Override
                    public void done(User user, AVException e) {
                        if (mView == null) {
                            return;
                        }
                        mView.updateUser(user);
                        refresh(user);
                    }
                });
    }

    private void refresh(User user) {
        AVQuery<ForumFollow> forumFollowCountAVQuery = ForumFollow.getQuery(ForumFollow.class);
        forumFollowCountAVQuery
                .whereEqualTo("owner", user)
                .countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, AVException e) {
                        if (mView == null) {
                            return;
                        }
                        if (e == null) {
                            mView.refreshFollowCount(i);
                        }
                    }
                });
        AVQuery<ForumFollow> forumFollowedCountAVQuery = ForumFollow.getQuery(ForumFollow.class);
        forumFollowedCountAVQuery
                .whereEqualTo("follower", user)
                .countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, AVException e) {
                        if (mView == null) {
                            return;
                        }
                        if (e == null) {
                            mView.refreshFollowedCount(i);
                        }
                    }
                });
        AVQuery<ForumPost> forumPostAVQuery = ForumPost.getQuery(ForumPost.class);
        forumPostAVQuery
                .whereEqualTo("author", user)
                .include("author")
                .findInBackground(new FindCallback<ForumPost>() {
                    @Override
                    public void done(List<ForumPost> list, AVException e) {
                        if (mView == null) {
                            return;
                        }
                        if (e == null) {
                            mView.refreshPostCount(list.size());
                            mView.refreshPostList(list);
                        }
                    }
                });
        AVQuery<ForumFollow> forumFollowAVQuery = ForumFollow.getQuery(ForumFollow.class);
        forumFollowAVQuery
                .whereEqualTo("follower", user)
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .getFirstInBackground(new GetCallback<ForumFollow>() {
                    @Override
                    public void done(ForumFollow forumFollow, AVException e) {
                        if (mView == null) {
                            return;
                        }
                        mForumFollow = forumFollow;
                        if (mForumFollow == null) {
                            mView.setUnFollow();
                        } else {
                            mView.setFollowing();
                        }
                    }
                });
    }

    @Override
    public void follow(User user) {
        if (user.equals(User.getCurrentUser(User.class))) {
            return;
        }
        mView.setFollowAble(false);
        if (mForumFollow == null) {
            // 关注
            mForumFollow = new ForumFollow();
            mForumFollow.setOwner(User.getCurrentUser(User.class));
            mForumFollow.setFollow(user);
            mForumFollow.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    mView.setFollowAble(true);
                    if (e == null) {
                        if (mView == null) {
                            return;
                        }
                        mView.setFollowing();
                    }
                }
            });
        } else {
            // 取消关注
            mForumFollow.deleteInBackground(new DeleteCallback() {
                @Override
                public void done(AVException e) {
                    mView.setFollowAble(true);
                    if (e == null) {
                        mForumFollow = null;
                        if (mView == null) {
                            return;
                        }
                        mView.setUnFollow();
                    }
                }
            });
        }
    }
}
