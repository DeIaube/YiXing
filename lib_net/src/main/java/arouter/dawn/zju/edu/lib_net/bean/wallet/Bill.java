package arouter.dawn.zju.edu.lib_net.bean.wallet;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.User;

@AVClassName("Bill")
public class Bill extends AVObject {

    public void setOwner(User user) {
        put("owner", user);
    }

    public User getOwner() {
        return getAVUser("owner", User.class);
    }

    /**
     *
     * @return true:收入 false:支出
     */
    public boolean getType() {
        return getBoolean("type");
    }

    public void setType(boolean type) {
        put("type", type);
    }

    public String getDeal() {
        return getString("deal");
    }

    public void setDeal(String deal) {
        put("deal", deal);
    }

    public String getRemarks() {
        return getString("remarks");
    }

    public void setRemarks(String remarks) {
        put("remarks", remarks);
    }

    public double getAmount() {
        return getDouble("amount");
    }

    public void setAmount(double amount) {
        put("amount", amount);
    }

}
