package arouter.dawn.zju.edu.lib_net.bean.forum;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.User;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户关注实体类
 */
@AVClassName("ForumFollow")
public class ForumFollow extends AVObject {

    public void setOwner(User user) {
        put("owner", user);
    }

    public User getOwner() {
        return getAVUser("owner", User.class);
    }

    public void setFollow(User user) {
        put("follower", user);
    }

    public User getFollow() {
        return getAVUser("follower", User.class);
    }

}
