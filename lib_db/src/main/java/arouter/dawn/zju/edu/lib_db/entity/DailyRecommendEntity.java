package arouter.dawn.zju.edu.lib_db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "daily_recommend_class")
public class DailyRecommendEntity extends BaseEntity{

    @ColumnInfo(name = "preview")
    private String preview;

    @ColumnInfo(name = "goodsId")
    private long goodsId;

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
