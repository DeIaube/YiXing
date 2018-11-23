package arouter.dawn.zju.edu.module_forum.ui.list;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface ForumListContract {

    interface View extends BaseContract.BaseView {

        void refresh(List<ForumPost> postList);
        void loadMore(List<ForumPost> postList);
        void showSwipeRefreshLayout();
        void hideSwipeRefreshLayout();

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void sendScrollUpEvent();
        void sendScrollDownEvent();
        void refresh(String tag);
        void loadMore(final String tag);

    }

}
