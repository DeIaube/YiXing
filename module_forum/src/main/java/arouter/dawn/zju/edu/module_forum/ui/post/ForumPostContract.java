package arouter.dawn.zju.edu.module_forum.ui.post;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.ForumPost;
import baselib.base.BaseContract;

public interface ForumPostContract {

    interface View extends BaseContract.BaseView {
        void hideCommentDialog();
        void refreshCommentList(List<ForumComment> commentList);
        void showCommentList();
        void hideCommentList();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void comment(ForumPost post, String comment);
        void cancelComment(String comment);
        void initCommentList(ForumPost post);
    }

}
