package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("ForumComment")
public class ForumComment extends AVObject {

    public void setOwner(User user) {
        put("owner", user);
    }

    public User getOwner() {
        return getAVUser("owner", User.class);
    }

    public void setConent(String conent) {
        put("conent", conent);
    }

    public String getConent() {
        return getString("conent");
    }

}
