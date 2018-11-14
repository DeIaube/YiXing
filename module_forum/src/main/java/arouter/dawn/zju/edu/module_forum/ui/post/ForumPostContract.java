package arouter.dawn.zju.edu.module_forum.ui.post;

import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import baselib.base.BaseContract;

public interface ForumPostContract {

    interface View extends BaseContract.BaseView {
        void hideCommentDialog();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void comment(ForumPost post, String comment);
        void cancelComment(String comment);
    }

}
