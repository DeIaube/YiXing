package arouter.dawn.zju.edu.lib_db.entity.forum;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import arouter.dawn.zju.edu.lib_db.entity.BaseEntity;

@Entity(tableName = "forum_post_report_class")
public class ForumPostReportEntity extends BaseEntity {

    @ColumnInfo(name = "ownerId")
    private String ownerId;
    @ColumnInfo(name = "postId")
    private String postId;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "type")
    private String type;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
