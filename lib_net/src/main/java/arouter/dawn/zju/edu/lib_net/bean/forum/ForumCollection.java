package arouter.dawn.zju.edu.lib_net.bean.forum;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.User;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户收藏帖子实体类
 *
 */
@AVClassName("ForumCollection")
public class ForumCollection extends AVObject {

    public void setOwner(User user) {
        put("owner", user);
    }

    public User getOwner() {
        return getAVUser("owner", User.class);
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
