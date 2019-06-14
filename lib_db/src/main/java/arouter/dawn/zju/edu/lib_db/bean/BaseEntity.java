package arouter.dawn.zju.edu.lib_db.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

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
