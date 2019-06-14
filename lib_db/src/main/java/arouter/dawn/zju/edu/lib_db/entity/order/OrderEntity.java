package arouter.dawn.zju.edu.lib_db.entity.order;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "order_class")
public class OrderEntity extends BaseEntity {

    @ColumnInfo(name = "ownerId")
    private String ownerId;
    @ColumnInfo(name = "goodsId")
    private String goodsId;
    @ColumnInfo(name = "type")
    private int type;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
