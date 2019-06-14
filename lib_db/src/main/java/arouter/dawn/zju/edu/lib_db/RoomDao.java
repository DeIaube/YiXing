package arouter.dawn.zju.edu.lib_db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import arouter.dawn.zju.edu.lib_db.entity.DailyRecommendEntity;
import arouter.dawn.zju.edu.lib_db.entity.NoticeEntity;
import arouter.dawn.zju.edu.lib_db.entity.UserEntity;

@Dao
public interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity... userEntities);

    @Delete
    void deleteUser(UserEntity userEntity);

    @Update
    void updateUser(UserEntity userEntity);

    @Query("SELECT * FROM user_class WHERE objectId = :id")
    UserEntity getUserById(String id);

    @Query("SELECT * FROM user_class")
    List<UserEntity> getAllUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNotice(NoticeEntity... noticeEntities);

    @Delete
    void deleteNotice(NoticeEntity noticeEntity);

    @Update
    void updateNotice(NoticeEntity noticeEntity);

    @Query("SELECT * FROM notice_class WHERE time = :time")
    NoticeEntity getUserByTime(String time);

    @Query("SELECT * FROM notice_class")
    List<NoticeEntity> getAllNoticeEntity();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDailyRecommend(DailyRecommendEntity... dailyRecommendEntity);

    @Delete
    void deleteDailyRecommend(DailyRecommendEntity dailyRecommendEntity);

    @Update
    void updateDailyRecommend(DailyRecommendEntity dailyRecommendEntity);

    @Query("SELECT * FROM daily_recommend_class WHERE objectId = :objectId")
    DailyRecommendEntity getDailyRecommendById(String objectId);

    @Query("SELECT * FROM daily_recommend_class")
    List<DailyRecommendEntity> getAllDailyRecommend();

}
