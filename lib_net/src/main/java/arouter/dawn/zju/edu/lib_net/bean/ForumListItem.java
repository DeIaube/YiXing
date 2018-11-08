package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.List;

@AVClassName("ForumListItem")
public class ForumListItem extends AVObject {

    public void setTitle(String title) {
        put("title", title);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTag(String tag) {
        put("tag", tag);
    }

    public String getTag() {
        return getString("tag");
    }

    public int getFabulous() {
        return getInt("fabulous");
    }

    public void setFabulous(int fabulous) {
        put("fabulous", fabulous);
    }

    public void setAuthor(User user) {
        put("author", user);
    }

    public User getAuthor() {
        return getAVUser("author", User.class);
    }

    public List<ForumComment> getCommentList() {
        return getList("comment_list", ForumComment.class);
    }

}
