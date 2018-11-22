package arouter.dawn.zju.edu.lib_net.bean.order;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import arouter.dawn.zju.edu.lib_net.bean.User;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 用户的满减券实体类
 */
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
