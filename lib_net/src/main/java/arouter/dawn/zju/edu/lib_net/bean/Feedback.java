package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("Feedback")
public class Feedback extends AVObject {

    public void setTitle(String title) {
        put("title", title);
    }

    public String getTitle() {
        return getString("title");
    }

    public void setContent(String content) {
        put("content", content);
    }

    public String getContent() {
        return getString("content");
    }

}
