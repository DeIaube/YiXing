package arouter.dawn.zju.edu.module_forum.ui.post;

import java.util.List;

import arouter.dawn.zju.edu.lib_net.bean.forum.ForumComment;
import arouter.dawn.zju.edu.lib_net.bean.forum.ForumPost;
import arouter.dawn.zju.edu.lib_net.bean.User;
import baselib.base.BaseContract;

public interface ForumPostContract {

    interface View extends BaseContract.BaseView {
        void hideCommentDialog();
        void refreshCommentList(List<ForumComment> commentList);
        void showCommentList();
        void hideCommentList();
        void showAuthorFollowing();
        void showAuthorUnFollow();
        void showPostAlreadyCollection();
        void showPostUnCollection();
        void setFollowBtnClickAble(boolean able);
        void setFollowBtnVisiable(boolean visiable);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void comment(ForumPost post, String comment);
        void cancelComment(String comment);
        void init(ForumPost post);
        void follow(User follower);
        void collection(ForumPost post);
        void report(ForumPost post, String type, String content);
    }

}
