package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVUser;

@AVClassName("User")
public class User extends AVUser {

    public void setPickName(String name) {
        put("pickname", name);
    }

    public String getPickName() {
        String name = getString("pickname");
        return name == null ? getMobilePhoneNumber() : name;
    }

    public void setPortrait(String portrait) {
        put("portrait", portrait);
    }

    public String getPortrait() {
        return getString("portrait");
    }

}
