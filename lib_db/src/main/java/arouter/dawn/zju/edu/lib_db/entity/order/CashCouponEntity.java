package arouter.dawn.zju.edu.lib_db.entity.order;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "cash_coupon_class")
public class CashCouponEntity extends BaseEntity {

    @ColumnInfo(name = "discount")
    private int discount;
    @ColumnInfo(name = "doorsill")
    private int doorsill;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "startData")
    private String startData;
    @ColumnInfo(name = "endData")
    private String endData;
    @ColumnInfo(name = "Integral")
    private int Integral;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDoorsill() {
        return doorsill;
    }

    public void setDoorsill(int doorsill) {
        this.doorsill = doorsill;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public int getIntegral() {
        return Integral;
    }

    public void setIntegral(int integral) {
        Integral = integral;
    }
}
