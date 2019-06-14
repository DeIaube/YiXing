package arouter.dawn.zju.edu.lib_db.entity.forum;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "forum_post_lick_class")
public class ForumPostLike extends BaseEntity {

    @ColumnInfo(name = "ownerId")
    private String ownerId;

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

    @ColumnInfo(name = "postId")
    private String postId;
}
