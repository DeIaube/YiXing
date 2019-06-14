package arouter.dawn.zju.edu.lib_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import arouter.dawn.zju.edu.lib_db.entity.NoticeEntity;
import arouter.dawn.zju.edu.lib_db.entity.UserEntity;

@Database(entities = {UserEntity.class, NoticeEntity.class}, version = 1,exportSchema = false)
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
