package arouter.dawn.zju.edu.lib_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import arouter.dawn.zju.edu.lib_db.entity.DailyRecommendEntity;
import arouter.dawn.zju.edu.lib_db.entity.NoticeEntity;
import arouter.dawn.zju.edu.lib_db.entity.UserEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumCollectionEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumCommentEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumFollowEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostLikeEntity;
import arouter.dawn.zju.edu.lib_db.entity.forum.ForumPostReportEntity;
import arouter.dawn.zju.edu.lib_db.entity.good.GoodsCollectionEntity;
import arouter.dawn.zju.edu.lib_db.entity.good.GoodsEntity;
import arouter.dawn.zju.edu.lib_db.entity.good.GoodsEvaluateEntity;
import arouter.dawn.zju.edu.lib_db.entity.order.CashCouponEntity;
import arouter.dawn.zju.edu.lib_db.entity.order.OrderEntity;
import arouter.dawn.zju.edu.lib_db.entity.order.UserCashCouponEntity;
import arouter.dawn.zju.edu.lib_db.entity.wallet.BillEntity;

@Database(entities = {UserEntity.class, NoticeEntity.class, DailyRecommendEntity.class,
        ForumCollectionEntity.class, ForumCommentEntity.class, ForumFollowEntity.class,
        ForumPostEntity.class, ForumPostLikeEntity.class, ForumPostReportEntity.class,
        BillEntity.class, GoodsEntity.class, GoodsCollectionEntity.class, GoodsEvaluateEntity.class,
        OrderEntity.class, CashCouponEntity.class, UserCashCouponEntity.class}, version = 1,exportSchema = false)
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
