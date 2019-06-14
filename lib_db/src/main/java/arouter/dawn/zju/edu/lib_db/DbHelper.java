package arouter.dawn.zju.edu.lib_db;

import android.content.Context;

import java.util.List;

import arouter.dawn.zju.edu.lib_db.entity.NoticeEntity;
import arouter.dawn.zju.edu.lib_db.entity.UserEntity;

public class DbHelper {

    private RoomDao dao;

    private DbHelper(Context context) {
        dao = RoomDatabase.getDatabase(context).getDao();
    }

    private static DbHelper instance;

    public static DbHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper(context);
                }
            }
        }
        return instance;
    }

    public UserEntity getUserById(String id) {
        return dao.getUserById(id);
    }

    public void saveUser(UserEntity userEntity) {
        dao.insertUser(userEntity);
    }

    public void saveNotice(NoticeEntity noticeEntity) {
        dao.insertNotice(noticeEntity);
    }

    public List<NoticeEntity> getAllNotice() {
        return dao.getAllNoticeEntity();
    }

}