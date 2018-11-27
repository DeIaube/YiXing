package arouter.dawn.zju.edu.module_forum.ui.user_information;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface ForumUserInformationContract {

    interface View extends BaseContract.BaseView {
        void refreshFollowCount(int count);
        void refreshFollowedCount(int count);
        void refreshPostCount(int count);
        void refreshPostList(List<ForumPost> postList);
        void setFollowing();
        void setUnFollow();
        void setFollowAble(boolean able);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refresh(User user);
        void follow(User user);
    }

}
