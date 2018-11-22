package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户反馈实体类
 */
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
