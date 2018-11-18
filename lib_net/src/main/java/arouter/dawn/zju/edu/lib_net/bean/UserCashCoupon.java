package arouter.dawn.zju.edu.lib_net.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("UserCashCoupon")
public class UserCashCoupon extends AVObject {

    public void setOwner(User user) {
        put("owner", user);
    }

    public User getOwner() {
        return getAVUser("owner", User.class);
    }

    public void setUsed(boolean used) {
        put("used", used);
    }

    public boolean getUsed() {
        return getBoolean("used");
    }

    public void setCashCoupon(CashCoupon cashCoupon) {
        put("cashCoupon", cashCoupon);
    }

    public CashCoupon getCashCoupon() {
        try {
            return getAVObject("cashCoupon", CashCoupon.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
