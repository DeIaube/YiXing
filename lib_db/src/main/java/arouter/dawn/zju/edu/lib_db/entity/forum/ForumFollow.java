package arouter.dawn.zju.edu.lib_db.entity.forum;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "forum_follow_class")
public class ForumFollow extends BaseEntity {

    @ColumnInfo(name = "ownerId")
    private String ownerId;
    @ColumnInfo(name = "followerId")
    private String followerId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }
}
