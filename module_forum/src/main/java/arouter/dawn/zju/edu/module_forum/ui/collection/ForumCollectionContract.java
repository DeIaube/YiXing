package arouter.dawn.zju.edu.module_forum.ui.collection;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import baselib.base.BaseContract;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 */
public interface ForumCollectionContract {

    interface View extends BaseContract.BaseView {
        void refreshCollectionPostList(List<ForumPost> postList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshCollectionPostList();
    }

}
