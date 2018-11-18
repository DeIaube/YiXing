package arouter.dawn.zju.edu.lib_net.bean.order;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.Date;

@AVClassName("CashCoupon")
public class CashCoupon extends AVObject {

    public void setDiscount(double discount) {
        put("discount", discount);
    }

    public double getDiscount() {
        return getDouble("discount");
    }

    public void setDoorsill(double doorsill) {
        put("doorsill", doorsill);
    }

    public double getDoorsill() {
        return getDouble("doorsill");
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

}
