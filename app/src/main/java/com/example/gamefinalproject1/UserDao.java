package com.example.gamefinalproject1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
@TypeConverters({ConvertDate.class})
public interface UserDao {
    @Insert
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
  void insertUser(UserTable user);
    @Update
    void updateUser(UserTable user);
    @Delete
    void deleteUser(UserTable user);
    @Query("select * from UserTable")
    LiveData<List<UserTable>> getAllUsers();

}
