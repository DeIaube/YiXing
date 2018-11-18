package arouter.dawn.zju.edu.module_forum.ui.list;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import baselib.base.BaseContract;

public interface ForumListContract {

    interface View extends BaseContract.BaseView {

        void refresh(List<ForumPost> postList);
        void showSwipeRefreshLayout();
        void hideSwipeRefreshLayout();

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void sendScrollUpEvent();
        void sendScrollDownEvent();
        void refresh(String tag);

    }

}
