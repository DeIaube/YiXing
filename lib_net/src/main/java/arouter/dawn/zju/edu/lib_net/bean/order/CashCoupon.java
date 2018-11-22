package arouter.dawn.zju.edu.lib_net.bean.order;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;

/**
 * @Auther: Dawn
 * @Date: 2018/11/22 22:01
 * @Description:
 * 满减券实体类
 */
@AVClassName("CashCoupon")
public class CashCoupon extends AVObject {

    public void setDiscount(int discount) {
        put("discount", discount);
    }

    public int getDiscount() {
        return getInt("discount");
    }

    public void setDoorsill(int doorsill) {
        put("doorsill", doorsill);
    }

    public int getDoorsill() {
        return getInt("doorsill");
    }

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public void setStartTime(Date date) {
        put("startDate", date);
    }

    public Date getStartTime() {
        return getDate("startDate");
    }

    public void setEndTime(Date date) {
        put("endDate", date);
    }

    public Date getEndTime() {
        return getDate("endDate");
    }

    public void setIntegral(int integral) {
        put("integral", integral);
    }

    public int getIntegral() {
        return getInt("integral");
    }

}
