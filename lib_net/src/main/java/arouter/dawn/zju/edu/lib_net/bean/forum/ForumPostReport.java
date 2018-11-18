package arouter.dawn.zju.edu.lib_net.bean.forum;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.User;

@AVClassName("ForumPostReport")
public class ForumPostReport extends AVObject {

    public void setOwner(User user) {
        put("owner", user);
    }

    public User getOwner() {
        return getAVUser("owner", User.class);
    }

    public void setPost(ForumPost post) {
        put("post", post);
    }

    public ForumPost getPost() {
        try {
            return getAVObject("post", ForumPost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setType(String type) {
        put("type", type);
    }

    public String getType() {
        return getString("type");
    }

    public void setContent(String content) {
        put("content", content);
    }

    public String getContent() {
        return getString("content");
    }
}
