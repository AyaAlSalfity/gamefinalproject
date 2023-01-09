package com.example.gamefinalproject1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLevelData(LevelTable Level);

    @Update
    void updateLevel(LevelTable table);
    @Delete
    void deleteLevel(LevelTable table);
    @Query("select * from LevelTable")
    LiveData<List<LevelTable>> getAllLevel();
}
