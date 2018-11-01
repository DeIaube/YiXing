package baselib.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVUser;

@AVClassName("User")
public class User extends AVUser {

    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        String name = getString("name");
        return name.isEmpty() ? getMobilePhoneNumber() : name;
    }

    public void setPortrait(String portrait) {
        put("portrait", portrait);
    }

    public String getPortrait() {
        return getString("portrait");
    }

}
