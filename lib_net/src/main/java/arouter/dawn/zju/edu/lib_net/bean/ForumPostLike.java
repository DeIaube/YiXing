package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("ForumPostLike")
public class ForumPostLike extends AVObject {

    public void setOwner(User user) {
        put("user", user);
    }

    public User getOwner() {
        return getAVUser("user", User.class);
    }

    public ForumPost getPost() {
        try {
            return getAVObject("post", ForumPost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPost(ForumPost post) {
        put("post", post);
    }
}
