package arouter.dawn.zju.edu.lib_db.entity.order;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "user_cash_coupon_class")
public class UserCashCouponEntity extends BaseEntity {

    @ColumnInfo(name = "ownerId")
    private String ownerId;
    @ColumnInfo(name = "used")
    private boolean used;
    @ColumnInfo(name = "cashCouponId")
    private String cashCouponId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getCashCouponId() {
        return cashCouponId;
    }

    public void setCashCouponId(String cashCouponId) {
        this.cashCouponId = cashCouponId;
    }
}
