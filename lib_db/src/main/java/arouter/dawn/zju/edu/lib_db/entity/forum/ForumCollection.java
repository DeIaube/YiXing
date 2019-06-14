package arouter.dawn.zju.edu.lib_db.entity.forum;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "forum_collection_class")
public class ForumCollection extends BaseEntity {

    @ColumnInfo(name = "ownerId")
    private String ownerId;
    @ColumnInfo(name = "postId")
    private String postId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
