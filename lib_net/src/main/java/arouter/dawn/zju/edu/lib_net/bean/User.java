package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVUser;

@AVClassName("User")
public class User extends AVUser {

    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        String name = getString("name");
        return name == null ? getMobilePhoneNumber() : name;
    }

    public void setPortrait(String portrait) {
        put("portrait", portrait);
    }

    public String getPortrait() {
        return getString("portrait");
    }

}
