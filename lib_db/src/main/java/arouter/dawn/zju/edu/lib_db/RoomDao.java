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
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumCollectionEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumCommentEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumFollowEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostLikeEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostReportEntity;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumCollection(ForumCollectionEntity... forumCollections);

    @Delete
    void deleteForumCollection(ForumCollectionEntity forumCollection);

    @Update
    void updateForumCollection(ForumCollectionEntity forumCollection);

    @Query("SELECT * FROM forum_collection_class WHERE objectId = :objectId")
    ForumCollectionEntity getForumCollectionById(String objectId);

    @Query("SELECT * FROM forum_collection_class")
    List<ForumCollectionEntity> getAllForumCollection();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumComment(ForumCommentEntity... forumComments);

    @Delete
    void deleteForumComment(ForumCommentEntity forumComment);

    @Update
    void updateForumComment(ForumCommentEntity forumComment);

    @Query("SELECT * FROM forum_comment_class WHERE objectId = :objectId")
    ForumCommentEntity getForumCommentById(String objectId);

    @Query("SELECT * FROM forum_comment_class")
    List<ForumCommentEntity> getAllForumComment();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumFollow(ForumFollowEntity... forumFollows);

    @Delete
    void deleteForumFollow(ForumFollowEntity forumFollow);

    @Update
    void updateForumFollow(ForumFollowEntity forumFollow);

    @Query("SELECT * FROM forum_follow_class WHERE objectId = :objectId")
    ForumFollowEntity getForumFollowById(String objectId);

    @Query("SELECT * FROM forum_follow_class")
    List<ForumFollowEntity> getAllForumFollow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumPost(ForumPostEntity... forumPosts);

    @Delete
    void deleteForumPost(ForumPostEntity forumPost);

    @Update
    void updateForumPost(ForumPostEntity forumPost);

    @Query("SELECT * FROM forum_post_class WHERE objectId = :objectId")
    ForumPostEntity getForumPostById(String objectId);

    @Query("SELECT * FROM forum_post_class")
    List<ForumPostEntity> getAllForumPost();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumPostLike(ForumPostLikeEntity... forumPostLikes);

    @Delete
    void deleteForumPostLike(ForumPostLikeEntity forumPostLike);

    @Update
    void updateForumPostLike(ForumPostLikeEntity forumPostLike);

    @Query("SELECT * FROM forum_post_like_class WHERE objectId = :objectId")
    ForumPostLikeEntity getForumPostLikeById(String objectId);

    @Query("SELECT * FROM forum_post_like_class")
    List<ForumPostLikeEntity> getAllForumPostLike();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumPostReport(ForumPostReportEntity... forumPostReports);

    @Delete
    void deleteForumPostReport(ForumPostReportEntity forumPostReport);

    @Update
    void updateForumPostReport(ForumPostReportEntity forumPostReport);

    @Query("SELECT * FROM forum_post_report_class WHERE objectId = :objectId")
    ForumPostReportEntity getForumPostReportById(String objectId);

    @Query("SELECT * FROM forum_post_report_class")
    List<ForumPostReportEntity> getAllForumPostReport();

}
