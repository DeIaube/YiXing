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
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumCollection;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumComment;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumFollow;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPost;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostLike;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostReport;

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
    void insertForumCollection(ForumCollection... forumCollections);

    @Delete
    void deleteForumCollection(ForumCollection forumCollection);

    @Update
    void updateForumCollection(ForumCollection forumCollection);

    @Query("SELECT * FROM forum_collection_class WHERE objectId = :objectId")
    ForumCollection getForumCollectionById(String objectId);

    @Query("SELECT * FROM forum_collection_class")
    List<ForumCollection> getAllForumCollection();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumComment(ForumComment... forumComments);

    @Delete
    void deleteForumComment(ForumComment forumComment);

    @Update
    void updateForumComment(ForumComment forumComment);

    @Query("SELECT * FROM forum_comment_class WHERE objectId = :objectId")
    ForumComment getForumCommentById(String objectId);

    @Query("SELECT * FROM forum_comment_class")
    List<ForumComment> getAllForumComment();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumFollow(ForumFollow... forumFollows);

    @Delete
    void deleteForumFollow(ForumFollow forumFollow);

    @Update
    void updateForumFollow(ForumFollow forumFollow);

    @Query("SELECT * FROM forum_follow_class WHERE objectId = :objectId")
    ForumFollow getForumFollowById(String objectId);

    @Query("SELECT * FROM forum_follow_class")
    List<ForumFollow> getAllForumFollow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumPost(ForumPost... forumPosts);

    @Delete
    void deleteForumPost(ForumPost forumPost);

    @Update
    void updateForumPost(ForumPost forumPost);

    @Query("SELECT * FROM forum_post_class WHERE objectId = :objectId")
    ForumPost getForumPostById(String objectId);

    @Query("SELECT * FROM forum_post_class")
    List<ForumPost> getAllForumPost();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumPostLike(ForumPostLike... forumPostLikes);

    @Delete
    void deleteForumPostLike(ForumPostLike forumPostLike);

    @Update
    void updateForumPostLike(ForumPostLike forumPostLike);

    @Query("SELECT * FROM forum_post_lick_class WHERE objectId = :objectId")
    ForumPostLike getForumPostLikeById(String objectId);

    @Query("SELECT * FROM forum_post_lick_class")
    List<ForumPostLike> getAllForumPostLike();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForumPostReport(ForumPostReport... forumPostReports);

    @Delete
    void deleteForumPostReport(ForumPostReport forumPostReport);

    @Update
    void updateForumPostReport(ForumPostReport forumPostReport);

    @Query("SELECT * FROM forum_post_report_class WHERE objectId = :objectId")
    ForumPostReport getForumPostReportById(String objectId);

    @Query("SELECT * FROM forum_post_report_class")
    List<ForumPostReport> getAllForumPostReport();

}
