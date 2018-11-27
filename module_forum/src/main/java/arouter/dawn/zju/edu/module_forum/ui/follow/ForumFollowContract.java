package arouter.dawn.zju.edu.module_forum.ui.follow;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface ForumFollowContract {

    interface View extends BaseContract.BaseView {
        void refreshFollowList(List<User> userList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshFollowList();
    }

}
