package arouter.dawn.zju.edu.lib_db.bean;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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

}
