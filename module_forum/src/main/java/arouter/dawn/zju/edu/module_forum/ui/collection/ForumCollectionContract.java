package arouter.dawn.zju.edu.module_forum.ui.collection;


import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import baselib.base.BaseContract;

public interface ForumCollectionContract {

    interface View extends BaseContract.BaseView {
        void refreshCollectionPostList(List<ForumPost> postList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refreshCollectionPostList();
    }

}
