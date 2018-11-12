package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.List;

@AVClassName("ForumPost")
public class ForumPost extends AVObject {

    public void setTitle(String title) {
        put("title", title);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setContent(String title) {
        put("content", title);
    }

    public String getContent() {
        return getString("content");
    }

    public void setImageList(List<String> imageList) {
        put("image_list", imageList);
    }

    public List<String> getImageList() {
        return (List<String>) get("image_list");
    }

    public void setTag(String tag) {
        put("tag", tag);
    }

    public String getTag() {
        return getString("tag");
    }

    public void setAuthor(User user) {
        put("author", user);
    }

    public User getAuthor() {
        return getAVUser("author", User.class);
    }

    public void setLikesUser(List<User> userList) {
        put("likes_user_list", userList);
    }

    public List<User> getLikesUser() {
        return getList("likes_user_list", User.class);
    }

    public void setCommentList(List<ForumComment> commentList) {
        put("comment_List", commentList);
    }

    public List<ForumComment> getCommentList() {
        return getList("comment_List", ForumComment.class);
    }

}
