package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVUser;

import java.util.Date;

@AVClassName("User")
public class User extends AVUser {

    public void setPickName(String name) {
        put("pickname", name);
    }

    public String getPickName() {
        String name = getString("pickname");
        return name == null ? getMobilePhoneNumber() : name;
    }

    public int getPayPassword() {
        return getInt("payPassword");
    }

    public void setPayPassword(int payPassword) {
        put("payPassword", payPassword);
    }

    public void setPortrait(String portrait) {
        put("portrait", portrait);
    }

    public Date getBirth() {
        return getDate("birth");
    }

    public void setBirth(Date date) {
        put("birth", date);
    }

    public int getShopPoint() {
        return getInt("shopPoint");
    }

    public void setShopPoint(int shopPoint) {
        put("shopPoint", shopPoint);
    }

    public String getPortrait() {
        String portrait = getString("portrait");
        return portrait == null ? 
                "http://5b0988e595225.cdn.sohucs.com/images/20171231/fe11a76f654549a78d00feb4d118d744.jpeg" : portrait;
    }

    public void setBalance(double balance) {
        put("balance", balance);
    }

    public double getBalance() {
        return getDouble("balance");
    }

}
