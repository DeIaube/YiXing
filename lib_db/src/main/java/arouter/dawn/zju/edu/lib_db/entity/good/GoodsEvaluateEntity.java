package arouter.dawn.zju.edu.lib_db.entity.good;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "goods_evaluate_class")
public class GoodsEvaluateEntity extends BaseEntity {

    @ColumnInfo(name = "goodsId")
    private String goodsId;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "level")
    private int level;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
