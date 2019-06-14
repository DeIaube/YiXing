package arouter.dawn.zju.edu.lib_db.entity.good;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "goods_collection_class")
public class GoodsCollectionEntity extends BaseEntity {

    @ColumnInfo(name = "goodsId")
    private String goodsId;
    @ColumnInfo(name = "ownerId")
    private String ownerId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
