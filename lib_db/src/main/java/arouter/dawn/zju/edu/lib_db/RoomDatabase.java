package arouter.dawn.zju.edu.lib_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import arouter.dawn.zju.edu.lib_db.entity.DailyRecommendEntity;
import arouter.dawn.zju.edu.lib_db.entity.NoticeEntity;
import arouter.dawn.zju.edu.lib_db.entity.UserEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumCollection;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumComment;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumFollow;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPost;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostLike;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostReport;

@Database(entities = {UserEntity.class, NoticeEntity.class, DailyRecommendEntity.class, ForumCollection.class, ForumComment.class, ForumFollow.class, ForumPost.class, ForumPostLike.class, ForumPostReport.class}, version = 1,exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static RoomDatabase sInstance;

    static RoomDatabase getDatabase(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class,
                    "yixing.db").build();
        }
        return sInstance;
    }

    public static void onDestroy() {
        sInstance = null;
    }

    abstract RoomDao getDao();

}
