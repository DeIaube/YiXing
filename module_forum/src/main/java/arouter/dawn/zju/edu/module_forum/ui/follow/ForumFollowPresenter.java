package arouter.dawn.zju.edu.module_forum.ui.follow;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumFollow;
import baselib.base.BasePresenter;
import baselib.util.LogUtil;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public class ForumFollowPresenter extends BasePresenter<ForumFollowContract.View> implements ForumFollowContract.Presenter {

    private String TAG = "ForumFollowPresenter";

    @Override
    public void refreshFollowList() {
        AVQuery<ForumFollow> forumFollowAVQuery = ForumFollow.getQuery(ForumFollow.class);
        forumFollowAVQuery
                .whereEqualTo("owner", User.getCurrentUser(User.class))
                .include("follower")
                .findInBackground(new FindCallback<ForumFollow>() {
            @Override
            public void done(List<ForumFollow> list, AVException e) {
                if (mView == null) {
                    return;
                }
                if (e == null) {
                    LogUtil.i(TAG, "refreshFollowList: " + list.toString());
                    List<User> userList = new ArrayList<>();
                    for (ForumFollow forumFollow : list) {
                        userList.add(forumFollow.getFollow());
                    }
                    mView.refreshFollowList(userList);
                } else {
                    LogUtil.e(TAG, e.getLocalizedMessage());
                    mView.showMessage(e.getLocalizedMessage());
                }
            }
        });
    }
}
