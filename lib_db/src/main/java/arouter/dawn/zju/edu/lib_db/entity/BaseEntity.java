package arouter.dawn.zju.edu.lib_db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

/**
 * @Auther: Dawn
 * @Date: 2019/6/14 22:01
 * @Description:
 * 基础entity
 */
public class BaseEntity {
    @PrimaryKey()
    @ColumnInfo(name = "objectId")
    @NonNull
    public String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
