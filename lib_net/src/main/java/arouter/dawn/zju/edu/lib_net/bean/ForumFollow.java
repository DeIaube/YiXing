package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

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
